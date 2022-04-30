package net.frozenblock.wilderwild.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.frozenblock.wilderwild.WildClientMod;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.SculkEchoerBlock;
import net.frozenblock.wilderwild.block.entity.HangingTendrilBlockEntity;
import net.frozenblock.wilderwild.registry.*;
import net.frozenblock.wilderwild.tag.WildBlockTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.LargeEntitySpawnHelper;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class AncientHornProjectileEntity extends PersistentProjectileEntity {
    private final TagKey<Block> NON_COLLIDE = WildBlockTags.HORN_PROJECTILE_NON_COLLIDE;
    private boolean shot;
    private boolean leftOwner;
    public int aliveTicks;
    public int prevAliveTicks;
    public double vecX;
    public double vecY;
    public double vecZ;
    private BlockState inBlockState;
    private static final int shriekerCooldown = 0;
    private static final int sensorCooldown = 0;
    private static final int echoerCooldown = 0;
    private static final int tendrilCooldown = 0;

    public AncientHornProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setSound(RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE);
    }
    public AncientHornProjectileEntity(World world, double x, double y, double z) {
        super(RegisterEntities.ANCIENT_HORN_PROJECTILE_ENTITY, x, y, z, world);
        this.setSound(RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE);
    }
    public List<Entity> collidingEntities() {
        return world.getOtherEntities(this, this.getBoundingBox().stretch(this.getVelocity()).expand(1.0D), this::canHit);
    }
    public void tick() {
        this.baseTick();
        if (this.aliveTicks>300) { this.remove(RemovalReason.DISCARDED); }
        this.prevAliveTicks=this.aliveTicks;
        ++this.aliveTicks;
        if (!this.shot) { this.shot = true; }
        if (!this.leftOwner) { this.leftOwner = this.shouldLeaveOwner(); }
        boolean bl = this.isNoClip();
        Vec3d vec3d = this.getVelocity();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            double d = vec3d.horizontalLength();
            this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
            this.setPitch((float)(MathHelper.atan2(vec3d.y, d) * 57.2957763671875D));
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
        }
        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = this.world.getBlockState(blockPos);
        Vec3d vec3d2;

        if (this.shake > 0) { --this.shake; }
        if (this.isTouchingWaterOrRain() || blockState.isOf(Blocks.POWDER_SNOW)) { this.extinguish(); }
            Vec3d vec3d3 = this.getPos();
            vec3d2 = vec3d3.add(vec3d);
            HitResult hitResult = this.world.raycast(new RaycastContext(vec3d3, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
            while(!this.isRemoved()) {
                List<Entity> entities = this.collidingEntities();
                Entity owner = this.getOwner();
                for (Entity entity : entities) {
                    if (!this.isRemoved() && entity != null && entity != owner) {
                        boolean shouldDamage = true;
                        if (owner != null) {
                            if (entity instanceof PlayerEntity player) {
                                if (player.isCreative()) { shouldDamage = false; }
                                if (owner instanceof PlayerEntity && !((PlayerEntity) owner).shouldDamagePlayer((PlayerEntity) entity)) { shouldDamage = false; }
                            }
                            if (entity.isInvulnerable()) { shouldDamage = false; }
                        }
                        if (shouldDamage) { this.hitEntity(entity); }
                    }
                } break;
            }
            if (!this.isRemoved() && hitResult != null && !bl) {
                this.onCollision(hitResult);
                this.velocityDirty = true;
            }
            vec3d = this.getVelocity();
            double e = vec3d.x;
            double f = vec3d.y;
            double g = vec3d.z;
            if (this.isCritical()) {
                for(int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.CRIT, this.getX() + e * (double)i / 4.0D, this.getY() + f * (double)i / 4.0D, this.getZ() + g * (double)i / 4.0D, -e, -f + 0.2D, -g);
                }
            }
            double h = this.getX() + e;
            double j = this.getY() + f;
            double k = this.getZ() + g;
            double l = vec3d.horizontalLength();
            if (bl) {
                this.setYaw((float)(MathHelper.atan2(-e, -g) * 57.2957763671875D));
            } else {
                this.setYaw((float)(MathHelper.atan2(e, g) * 57.2957763671875D));
            }
            this.setPitch((float)(MathHelper.atan2(f, l) * 57.2957763671875D));
            this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
            this.setYaw(updateRotation(this.prevYaw, this.getYaw()));

            this.setPosition(h, j, k);
            this.checkBlockCollision();
        }
    public void setCooldown(int i) {
        Entity entity = this.getOwner();
        if (entity!=null) {
            if (entity instanceof PlayerEntity user) {
                user.getItemCooldownManager().set(RegisterItems.ANCIENT_HORN, i);
            }
        }
    }
    public boolean canHit(Entity entity) {
        if (!entity.isSpectator() && entity.isAlive() && entity.collides() && !(entity instanceof ProjectileEntity)) {
            Entity entity2 = this.getOwner();
            return entity2 == null || this.leftOwner || !entity2.isConnectedThroughVehicle(entity);
        } else {
            return false;
        }
    }
    public void onPlayerCollision(PlayerEntity player) { }
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.inBlockState = this.world.getBlockState(blockHitResult.getBlockPos());
        BlockState blockState = this.world.getBlockState(blockHitResult.getBlockPos());
        blockState.onProjectileHit(this.world, blockState, blockHitResult, this);
        Vec3d vec3d = blockHitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(vec3d);
        Vec3d vec3d2 = vec3d.normalize().multiply(0.05000000074505806D);
        this.setPos(this.getX() - vec3d2.x, this.getY() - vec3d2.y, this.getZ() - vec3d2.z);
        this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shake = 7;
        this.setCritical(false);
        Entity owner = this.getOwner();
        if (world instanceof ServerWorld server) {
            if (blockState.getBlock() == Blocks.SCULK_SHRIEKER) {
                BlockPos pos = blockHitResult.getBlockPos();
                if (blockState.get(RegisterProperties.SOULS_TAKEN) < 2 && !blockState.get(SculkShriekerBlock.SHRIEKING)) {
                    server.setBlockState(pos, blockState.with(RegisterProperties.SOULS_TAKEN, blockState.get(RegisterProperties.SOULS_TAKEN) + 1));
                    server.spawnParticles(ParticleTypes.SCULK_SOUL, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.15D, (double) pos.getZ() + 0.5D, 2, 0.2D, 0.0D, 0.2D, 0.0D);
                    trySpawnWarden(server, pos);
                    WardenEntity.addDarknessToClosePlayers(server, Vec3d.ofCenter(this.getBlockPos()), null, 40);
                    server.syncWorldEvent(3007, pos, 0);
                    server.emitGameEvent(GameEvent.SHRIEK, pos, GameEvent.Emitter.of(owner));
                    setCooldown(shriekerCooldown);
                    this.setSound(RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE);
                    this.setShotFromCrossbow(false);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
            if (blockState.getBlock() == Blocks.SCULK_SENSOR) {
                BlockPos pos = blockHitResult.getBlockPos();
                server.setBlockState(pos, blockState.with(RegisterProperties.NOT_HICCUPPING, false));
                if (SculkSensorBlock.isInactive(blockState)) {
                    SculkSensorBlock.setActive(null, world, pos, world.getBlockState(pos), (int) (Math.random() * 15));
                    world.emitGameEvent(owner, GameEvent.SCULK_SENSOR_TENDRILS_CLICKING, pos);
                    world.emitGameEvent(owner, WilderWild.SCULK_SENSOR_ACTIVATE, pos);
                    setCooldown(sensorCooldown);
                }
            }
            if (blockState.getBlock() == RegisterBlocks.SCULK_ECHOER) {
                BlockPos pos = blockHitResult.getBlockPos();
                if (SculkEchoerBlock.isInactive(blockState)) {
                    SculkEchoerBlock.setActive(owner, world, pos, world.getBlockState(pos), server.random.nextBetween(200, 360));
                    setCooldown(echoerCooldown);
                }
            }
        }
        this.setSound(RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE);
        this.setShotFromCrossbow(false);
        this.remove(RemovalReason.DISCARDED);
    }
    private static void trySpawnWarden(ServerWorld world, BlockPos pos) {
        if (world.getGameRules().getBoolean(GameRules.DO_WARDEN_SPAWNING)) {
            LargeEntitySpawnHelper.trySpawnAt(EntityType.WARDEN, SpawnReason.TRIGGERED, world, pos, 20, 5, 6).ifPresent((entity) -> {
                entity.playSound(SoundEvents.ENTITY_WARDEN_AGITATED, 5.0F, 1.0F);
            });
        }
    }
    protected SoundEvent getHitSound() {
        return RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE;
    }
    public boolean isTouchingWater() { return true; }
    public boolean isNoClip() {
        BlockState insideState = world.getBlockState(this.getBlockPos());
        if (insideState.isOf(RegisterBlocks.HANGING_TENDRIL) && world instanceof ServerWorld server) {
            BlockPos pos = this.getBlockPos();
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof HangingTendrilBlockEntity tendril) {
                this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                int XP = tendril.storedXP;
                if (XP>0) {
                    tendril.storedXP = 0;
                    world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3, Explosion.DestructionType.BREAK);
                    ExperienceOrbEntity.spawn(server, Vec3d.ofCenter(pos).add(0, 0, 0), XP);
                    setCooldown(tendrilCooldown);
                    this.setShotFromCrossbow(false);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        }
        if (insideState.isIn(this.NON_COLLIDE)) {
            if (world instanceof ServerWorld server) {
                if (insideState.isOf(Blocks.BELL)) {
                    ((BellBlock) insideState.getBlock()).onProjectileHit(server, insideState, this.world.raycast(new RaycastContext(this.getPos(), new Vec3d(this.getBlockX(), this.getBlockY(), this.getBlockZ()), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this)), this);
                }
            } return true;
        }
        Vec3d vec3d3 = this.getPos();
        Vec3d vec3d = this.getVelocity();
        Vec3d vec3d2 = vec3d3.add(vec3d.multiply(0.08));
        HitResult hitResult = this.world.raycast(new RaycastContext(vec3d3, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockState state = world.getBlockState(((BlockHitResult)hitResult).getBlockPos());
            if (state.isIn(this.NON_COLLIDE)) {
                return true;
            }
        } return false;
    }
    private boolean shouldLeaveOwner() {
        Entity entity = this.getOwner();
        if (entity != null) {
            for (Entity entity2 : this.world.getOtherEntities(this, this.getBoundingBox().stretch(this.getVelocity()).expand(1.0D), (entityx) -> !entityx.isSpectator() && entityx.collides())) {
                if (entity2.getRootVehicle() == entity.getRootVehicle()) { return false; }
            }
        } return true;
    }
    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, WildClientMod.HORN_PROJECTILE_PACKET_ID);
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
    public void writeCustomDataToNbt(NbtCompound nbt) {
        if (!this.isRemoved()) {
            if (this.inBlockState != null) {
                nbt.put("inBlockState", NbtHelper.fromBlockState(this.inBlockState));
            }
            nbt.putInt("aliveTicks", this.aliveTicks);
            if (this.leftOwner) {
                nbt.putBoolean("LeftOwner", true);
            }
            nbt.putBoolean("HasBeenShot", this.shot);
            nbt.putDouble("originX", this.vecX);
            nbt.putDouble("originY", this.vecY);
            nbt.putDouble("originZ", this.vecZ);
        }
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        if (!this.isRemoved()) {
            if (nbt.contains("inBlockState", 10)) {
                this.inBlockState = NbtHelper.toBlockState(nbt.getCompound("inBlockState"));
            }
            this.aliveTicks = nbt.getInt("aliveTicks");
            this.leftOwner = nbt.getBoolean("LeftOwner");
            this.shot = nbt.getBoolean("HasBeenShot");
            this.vecX = nbt.getDouble("originX");
            this.vecY = nbt.getDouble("originY");
            this.vecZ = nbt.getDouble("originZ");
        }
    }
    public void setVelocity(Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch + roll) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setVelocity(f, g, h, speed, divergence);
        this.vecX = shooter.getX();
        this.vecY = shooter.getEyeY();
        this.vecZ = shooter.getZ();
        this.setOwner(shooter);
    }
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            if (!world.getBlockState(blockHitResult.getBlockPos()).isIn(this.NON_COLLIDE)) {
                this.onBlockHit(blockHitResult);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }
    public double getDamage() {
        double distance = Math.sqrt(this.getBlockPos().getSquaredDistance(new Vec3d(this.vecX, this.vecY, this.vecZ)));
        distance = MathHelper.clamp(distance, 7.8, 45);
        return 15*Math.sin((distance*Math.PI)/50);
    }
    protected float getDragInWater() { return 1.0F; }
    public boolean hasNoGravity() { return true; }
    private void hitEntity(Entity entity) {
        int i = (int) this.getDamage();
        Entity entity2 = this.getOwner();
        if (entity != entity2) {
            DamageSource damageSource;
            if (entity2 == null) {
                damageSource = VibrationDamageSource.ancientHornDamageSource(this, this);
            } else {
                damageSource = VibrationDamageSource.ancientHornDamageSource(this, entity2);
                if (entity2 instanceof LivingEntity) {
                    ((LivingEntity) entity2).onAttacking(entity);
                }
            }
            boolean bl = entity.getType() == EntityType.ENDERMAN;
            int j = entity.getFireTicks();
            if (this.isOnFire() && !bl) { entity.setOnFireFor(5); }
            if (entity instanceof WardenEntity warden && entity2!=null) {
                warden.increaseAngerAt(entity2, 100, true);
            } else if (entity.damage(damageSource, (float) i)) {
                if (bl) { return; }
                if (entity instanceof LivingEntity livingEntity) {
                    if (!this.world.isClient && entity2 instanceof LivingEntity) {
                        EnchantmentHelper.onUserDamaged(livingEntity, entity2);
                        EnchantmentHelper.onTargetDamaged((LivingEntity) entity2, livingEntity);
                    }
                    this.onHit(livingEntity);
                    if (livingEntity instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                        ((ServerPlayerEntity) entity2).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, 0.0F));
                    }
                }

                this.playSound(RegisterSounds.ANCIENT_HORN_VIBRATION_DISSAPATE, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            } else {
                entity.setFireTicks(j);
                if (!this.world.isClient && this.getVelocity().lengthSquared() < 1.0E-7D) {
                    this.discard();
                }
            }
        }
    }
    public class EntitySpawnPacket { //When the Fabric tutorial WORKS!!!!! BOM BOM BOM BOM BOM BOM BOM, BOBOBOM! DUNDUN!
        public static Packet<?> create(Entity e, Identifier packetID) {
            if (e.world.isClient)
                throw new IllegalStateException("SpawnPacketUtil.create called on the logical client!");
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeVarInt(Registry.ENTITY_TYPE.getRawId(e.getType()));
            byteBuf.writeUuid(e.getUuid());
            byteBuf.writeVarInt(e.getId());
            PacketBufUtil.writeVec3d(byteBuf, e.getPos());
            PacketBufUtil.writeAngle(byteBuf, e.getPitch());
            PacketBufUtil.writeAngle(byteBuf, e.getYaw());
            return ServerPlayNetworking.createS2CPacket(packetID, byteBuf);
        }

        public static final class PacketBufUtil {

            public static byte packAngle(float angle) {
                return (byte) MathHelper.floor(angle * 256 / 360);
            }

            public static float unpackAngle(byte angleByte) {
                return (angleByte * 360) / 256f;
            }

            public static void writeAngle(PacketByteBuf byteBuf, float angle) {
                byteBuf.writeByte(packAngle(angle));
            }

            public static float readAngle(PacketByteBuf byteBuf) {
                return unpackAngle(byteBuf.readByte());
            }

            public static void writeVec3d(PacketByteBuf byteBuf, Vec3d vec3d) {
                byteBuf.writeDouble(vec3d.x);
                byteBuf.writeDouble(vec3d.y);
                byteBuf.writeDouble(vec3d.z);
            }

            public static Vec3d readVec3d(PacketByteBuf byteBuf) {
                double x = byteBuf.readDouble();
                double y = byteBuf.readDouble();
                double z = byteBuf.readDouble();
                return new Vec3d(x, y, z);
            }
        }
    }
}
