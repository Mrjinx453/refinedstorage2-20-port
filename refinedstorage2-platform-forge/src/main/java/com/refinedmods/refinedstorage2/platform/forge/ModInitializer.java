package com.refinedmods.refinedstorage2.platform.forge;

import com.refinedmods.refinedstorage2.platform.api.PlatformApi;
import com.refinedmods.refinedstorage2.platform.common.AbstractModInitializer;
import com.refinedmods.refinedstorage2.platform.common.Platform;
import com.refinedmods.refinedstorage2.platform.common.block.AbstractBaseBlock;
import com.refinedmods.refinedstorage2.platform.common.block.AbstractStorageBlock;
import com.refinedmods.refinedstorage2.platform.common.block.CableBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ControllerBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ControllerType;
import com.refinedmods.refinedstorage2.platform.common.block.DestructorBlock;
import com.refinedmods.refinedstorage2.platform.common.block.DetectorBlock;
import com.refinedmods.refinedstorage2.platform.common.block.DiskDriveBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ExporterBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ExternalStorageBlock;
import com.refinedmods.refinedstorage2.platform.common.block.FluidStorageBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ImporterBlock;
import com.refinedmods.refinedstorage2.platform.common.block.InterfaceBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ItemStorageBlock;
import com.refinedmods.refinedstorage2.platform.common.block.SimpleBlock;
import com.refinedmods.refinedstorage2.platform.common.block.entity.CableBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.ControllerBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.ImporterBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.InterfaceBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.destructor.DestructorBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.detector.DetectorBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.diskdrive.AbstractDiskDriveBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.exporter.ExporterBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.externalstorage.ExternalStorageBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.grid.CraftingGridBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.grid.GridBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.storage.FluidStorageBlockBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.entity.storage.ItemStorageBlockBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.grid.CraftingGridBlock;
import com.refinedmods.refinedstorage2.platform.common.block.grid.GridBlock;
import com.refinedmods.refinedstorage2.platform.common.block.ticker.ControllerBlockEntityTicker;
import com.refinedmods.refinedstorage2.platform.common.containermenu.ControllerContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.DestructorContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.ExporterContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.ImporterContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.InterfaceContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.detector.DetectorContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.grid.CraftingGridContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.grid.GridContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.ExternalStorageContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.block.FluidStorageBlockContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.block.ItemStorageBlockContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.diskdrive.DiskDriveContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.content.BlockEntities;
import com.refinedmods.refinedstorage2.platform.common.content.Blocks;
import com.refinedmods.refinedstorage2.platform.common.content.CreativeModeTabItems;
import com.refinedmods.refinedstorage2.platform.common.content.Items;
import com.refinedmods.refinedstorage2.platform.common.content.LootFunctions;
import com.refinedmods.refinedstorage2.platform.common.content.Menus;
import com.refinedmods.refinedstorage2.platform.common.content.Sounds;
import com.refinedmods.refinedstorage2.platform.common.internal.storage.channel.StorageChannelTypes;
import com.refinedmods.refinedstorage2.platform.common.internal.storage.type.FluidStorageType;
import com.refinedmods.refinedstorage2.platform.common.internal.storage.type.ItemStorageType;
import com.refinedmods.refinedstorage2.platform.common.item.FluidStorageDiskItem;
import com.refinedmods.refinedstorage2.platform.common.item.FortuneUpgradeItem;
import com.refinedmods.refinedstorage2.platform.common.item.ItemStorageDiskItem;
import com.refinedmods.refinedstorage2.platform.common.item.ProcessorItem;
import com.refinedmods.refinedstorage2.platform.common.item.SimpleItem;
import com.refinedmods.refinedstorage2.platform.common.item.SimpleUpgradeItem;
import com.refinedmods.refinedstorage2.platform.common.item.WrenchItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.ControllerBlockItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.CreativeControllerBlockItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.FluidStorageBlockBlockItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.ItemStorageBlockBlockItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.NamedBlockItem;
import com.refinedmods.refinedstorage2.platform.common.item.block.SimpleBlockItem;
import com.refinedmods.refinedstorage2.platform.common.recipe.UpgradeWithEnchantedBookRecipeSerializer;
import com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil;
import com.refinedmods.refinedstorage2.platform.common.util.TickHandler;
import com.refinedmods.refinedstorage2.platform.forge.block.entity.ForgeDiskDriveBlockEntity;
import com.refinedmods.refinedstorage2.platform.forge.internal.grid.FluidGridExtractionStrategy;
import com.refinedmods.refinedstorage2.platform.forge.internal.grid.FluidGridInsertionStrategy;
import com.refinedmods.refinedstorage2.platform.forge.internal.grid.ItemGridExtractionStrategy;
import com.refinedmods.refinedstorage2.platform.forge.internal.grid.ItemGridScrollingStrategy;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.exporter.FluidHandlerExporterTransferStrategyFactory;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.exporter.ItemHandlerExporterTransferStrategyFactory;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.externalstorage.FluidHandlerPlatformExternalStorageProviderFactory;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.externalstorage.ItemHandlerPlatformExternalStorageProviderFactory;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.importer.FluidHandlerImporterTransferStrategyFactory;
import com.refinedmods.refinedstorage2.platform.forge.internal.network.node.importer.ItemHandlerImporterTransferStrategyFactory;
import com.refinedmods.refinedstorage2.platform.forge.packet.NetworkManager;

import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.CABLE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.CONSTRUCTION_CORE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.CONTROLLER;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.CRAFTING_GRID;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.CREATIVE_CONTROLLER;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.DESTRUCTION_CORE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.DESTRUCTOR;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.DETECTOR;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.DISK_DRIVE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.EXPORTER;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.EXTERNAL_STORAGE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.FLUID_STORAGE_BLOCK;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.FORTUNE_1_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.FORTUNE_2_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.FORTUNE_3_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.GRID;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.IMPORTER;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.INTERFACE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.ITEM_STORAGE_BLOCK;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.MACHINE_CASING;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.PROCESSOR_BINDING;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.QUARTZ_ENRICHED_IRON;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.QUARTZ_ENRICHED_IRON_BLOCK;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.SILICON;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.SILK_TOUCH_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.SPEED_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.STACK_UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.STORAGE_BLOCK;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.STORAGE_HOUSING;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.UPGRADE;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.WRENCH;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forFluidStorageBlock;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forFluidStorageDisk;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forFluidStoragePart;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forItemStorageBlock;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forItemStoragePart;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forProcessor;
import static com.refinedmods.refinedstorage2.platform.common.content.ContentIds.forStorageDisk;
import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createIdentifier;
import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createTranslation;

@Mod(IdentifierUtil.MOD_ID)
public class ModInitializer extends AbstractModInitializer {
    private static final String BLOCK_TRANSLATION_CATEGORY = "block";

    private final DeferredRegister<Block> blockRegistry =
        DeferredRegister.create(ForgeRegistries.BLOCKS, IdentifierUtil.MOD_ID);
    private final DeferredRegister<Item> itemRegistry =
        DeferredRegister.create(ForgeRegistries.ITEMS, IdentifierUtil.MOD_ID);
    private final DeferredRegister<BlockEntityType<?>> blockEntityTypeRegistry =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IdentifierUtil.MOD_ID);
    private final DeferredRegister<MenuType<?>> menuTypeRegistry =
        DeferredRegister.create(ForgeRegistries.MENU_TYPES, IdentifierUtil.MOD_ID);
    private final DeferredRegister<SoundEvent> soundEventRegistry =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IdentifierUtil.MOD_ID);
    private final DeferredRegister<RecipeSerializer<?>> recipeSerializerRegistry =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, IdentifierUtil.MOD_ID);

    public ModInitializer() {
        initializePlatform(new PlatformImpl(new NetworkManager()));
        initializePlatformApi();
        registerAdditionalStorageTypes();
        registerAdditionalStorageChannelTypes();
        registerAdditionalFilteredResourceFactories();
        registerDestructorStrategyFactories();
        registerAdditionalGridInsertionStrategyFactories();
        registerGridExtractionStrategyFactories();
        registerGridScrollingStrategyFactories();
        registerNetworkComponents();
        registerImporterTransferStrategyFactories();
        registerExporterTransferStrategyFactories();
        registerExternalStorageProviderFactories();
        registerContent();
        registerSounds();
        registerRecipeSerializers();
        registerTickHandler();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModInitializer::onClientSetup);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModInitializer::onRegisterModelGeometry);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientModInitializer::onRegisterKeyMappings);
        });

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegister);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterCreativeModeTab);
        MinecraftForge.EVENT_BUS.addListener(this::onRightClickBlock);
        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, this::registerCapabilities);
    }

    private void registerAdditionalGridInsertionStrategyFactories() {
        PlatformApi.INSTANCE.addGridInsertionStrategyFactory(FluidGridInsertionStrategy::new);
    }

    private void registerGridExtractionStrategyFactories() {
        PlatformApi.INSTANCE.addGridExtractionStrategyFactory(
            (containerMenu, player, gridServiceFactory, containerExtractionSource) ->
                new ItemGridExtractionStrategy(containerMenu, player, gridServiceFactory)
        );
        PlatformApi.INSTANCE.addGridExtractionStrategyFactory(FluidGridExtractionStrategy::new);
    }

    private void registerGridScrollingStrategyFactories() {
        PlatformApi.INSTANCE.addGridScrollingStrategyFactory(ItemGridScrollingStrategy::new);
    }

    private void registerImporterTransferStrategyFactories() {
        PlatformApi.INSTANCE.getImporterTransferStrategyRegistry().register(
            createIdentifier("item"),
            new ItemHandlerImporterTransferStrategyFactory()
        );
        PlatformApi.INSTANCE.getImporterTransferStrategyRegistry().register(
            createIdentifier("fluid"),
            new FluidHandlerImporterTransferStrategyFactory()
        );
    }

    private void registerExporterTransferStrategyFactories() {
        PlatformApi.INSTANCE.getExporterTransferStrategyRegistry().register(
            createIdentifier("item"),
            new ItemHandlerExporterTransferStrategyFactory()
        );
        PlatformApi.INSTANCE.getExporterTransferStrategyRegistry().register(
            createIdentifier("fluid"),
            new FluidHandlerExporterTransferStrategyFactory()
        );
    }

    private void registerExternalStorageProviderFactories() {
        PlatformApi.INSTANCE.addExternalStorageProviderFactory(
            StorageChannelTypes.ITEM,
            new ItemHandlerPlatformExternalStorageProviderFactory()
        );
        PlatformApi.INSTANCE.addExternalStorageProviderFactory(
            StorageChannelTypes.FLUID,
            new FluidHandlerPlatformExternalStorageProviderFactory()
        );
    }

    private void registerContent() {
        registerBlocks();
        registerItems();
        registerBlockEntities();
        registerMenus();
    }

    private void registerBlocks() {
        Blocks.INSTANCE.getCable().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getCable().getId(color, CABLE).getPath(),
            () -> new CableBlock(color, Blocks.INSTANCE.getCable().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "cable"))
            ))
        );
        Blocks.INSTANCE.setQuartzEnrichedIronBlock(blockRegistry.register(
            QUARTZ_ENRICHED_IRON_BLOCK.getPath(),
            SimpleBlock::new
        ));
        Blocks.INSTANCE.setDiskDrive(blockRegistry.register(
            DISK_DRIVE.getPath(),
            () -> new DiskDriveBlock(ForgeDiskDriveBlockEntity::new)
        ));
        Blocks.INSTANCE.setMachineCasing(blockRegistry.register(
            MACHINE_CASING.getPath(),
            SimpleBlock::new
        ));
        Blocks.INSTANCE.getGrid().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getGrid().getId(color, GRID).getPath(),
            () -> new GridBlock(
                Blocks.INSTANCE.getGrid().getName(
                    color,
                    createTranslation(BLOCK_TRANSLATION_CATEGORY, "grid")
                ),
                color
            )
        ));
        Blocks.INSTANCE.getCraftingGrid().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getCraftingGrid().getId(color, CRAFTING_GRID).getPath(),
            () -> new CraftingGridBlock(
                Blocks.INSTANCE.getCraftingGrid().getName(
                    color,
                    createTranslation(BLOCK_TRANSLATION_CATEGORY, "crafting_grid")
                ),
                color
            )
        ));
        Blocks.INSTANCE.getDetector().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getDetector().getId(color, DETECTOR).getPath(),
            () -> new DetectorBlock(
                color,
                Blocks.INSTANCE.getDetector().getName(
                    color,
                    createTranslation(BLOCK_TRANSLATION_CATEGORY, "detector")
                )
            )
        ));
        Blocks.INSTANCE.getController().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getController().getId(color, CONTROLLER).getPath(),
            () -> new ControllerBlock(
                ControllerType.NORMAL,
                Blocks.INSTANCE.getController().getName(
                    color,
                    createTranslation(BLOCK_TRANSLATION_CATEGORY, "controller")
                ),
                new ControllerBlockEntityTicker(BlockEntities.INSTANCE::getController),
                color
            )
        ));
        Blocks.INSTANCE.getCreativeController().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getCreativeController().getId(color, CREATIVE_CONTROLLER).getPath(),
            () -> new ControllerBlock(
                ControllerType.CREATIVE,
                Blocks.INSTANCE.getCreativeController().getName(
                    color,
                    createTranslation(BLOCK_TRANSLATION_CATEGORY, "creative_controller")
                ),
                new ControllerBlockEntityTicker(BlockEntities.INSTANCE::getCreativeController),
                color
            )
        ));

        for (final ItemStorageType.Variant variant : ItemStorageType.Variant.values()) {
            Blocks.INSTANCE.setItemStorageBlock(variant, blockRegistry
                .register(forItemStorageBlock(variant).getPath(),
                    () -> new ItemStorageBlock(variant)
                ));
        }

        for (final FluidStorageType.Variant variant : FluidStorageType.Variant.values()) {
            Blocks.INSTANCE.setFluidStorageBlock(variant, blockRegistry.register(
                forFluidStorageBlock(variant).getPath(),
                () -> new FluidStorageBlock(variant)
            ));
        }

        Blocks.INSTANCE.getImporter().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getImporter().getId(color, IMPORTER).getPath(),
            () -> new ImporterBlock(color, Blocks.INSTANCE.getImporter().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "importer")
            ))
        ));
        Blocks.INSTANCE.getExporter().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getExporter().getId(color, EXPORTER).getPath(),
            () -> new ExporterBlock(color, Blocks.INSTANCE.getExporter().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "exporter")
            ))
        ));
        Blocks.INSTANCE.setInterface(blockRegistry.register(INTERFACE.getPath(), InterfaceBlock::new));
        Blocks.INSTANCE.getExternalStorage().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getExternalStorage().getId(color, EXTERNAL_STORAGE).getPath(),
            () -> new ExternalStorageBlock(color, Blocks.INSTANCE.getExternalStorage().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "external_storage")
            ))
        ));
        Blocks.INSTANCE.getDestructor().putAll(color -> blockRegistry.register(
            Blocks.INSTANCE.getDestructor().getId(color, DESTRUCTOR).getPath(),
            () -> new DestructorBlock(color, Blocks.INSTANCE.getDestructor().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "destructor")
            ))
        ));

        blockRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerItems() {
        registerSimpleItems();
        registerGridItems();
        registerCableItems();
        registerControllerItems();
        registerDetectorItems();
        registerImporterItems();
        registerExporterItems();
        registerExternalStorageItems();
        registerDestructorItems();
        registerStorageItems();
        registerUpgrades();

        itemRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerSimpleItems() {
        Items.INSTANCE.setQuartzEnrichedIron(itemRegistry.register(
            QUARTZ_ENRICHED_IRON.getPath(),
            SimpleItem::new
        ));
        itemRegistry.register(
            QUARTZ_ENRICHED_IRON_BLOCK.getPath(),
            () -> new SimpleBlockItem(Blocks.INSTANCE.getQuartzEnrichedIronBlock())
        );
        Items.INSTANCE.setSilicon(itemRegistry.register(
            SILICON.getPath(),
            SimpleItem::new
        ));
        Items.INSTANCE.setProcessorBinding(itemRegistry.register(
            PROCESSOR_BINDING.getPath(),
            SimpleItem::new
        ));
        itemRegistry.register(
            DISK_DRIVE.getPath(),
            () -> new SimpleBlockItem(Blocks.INSTANCE.getDiskDrive())
        );
        Items.INSTANCE.setWrench(itemRegistry.register(
            WRENCH.getPath(),
            WrenchItem::new
        ));
        Items.INSTANCE.setStorageHousing(itemRegistry.register(
            STORAGE_HOUSING.getPath(),
            SimpleItem::new
        ));
        itemRegistry.register(
            MACHINE_CASING.getPath(),
            () -> new SimpleBlockItem(Blocks.INSTANCE.getMachineCasing())
        );
        for (final ProcessorItem.Type type : ProcessorItem.Type.values()) {
            Items.INSTANCE.setProcessor(
                type,
                itemRegistry.register(forProcessor(type).getPath(), ProcessorItem::new)
            );
        }
        itemRegistry.register(
            INTERFACE.getPath(),
            () -> new SimpleBlockItem(Blocks.INSTANCE.getInterface())
        );
        Items.INSTANCE.setConstructionCore(itemRegistry.register(CONSTRUCTION_CORE.getPath(), SimpleItem::new));
        Items.INSTANCE.setDestructionCore(itemRegistry.register(DESTRUCTION_CORE.getPath(), SimpleItem::new));
    }

    private void registerCableItems() {
        Blocks.INSTANCE.getCable().forEach((color, block) -> Items.INSTANCE.addCable(itemRegistry.register(
            Blocks.INSTANCE.getCable().getId(color, CABLE).getPath(),
            () -> new NamedBlockItem(block.get(), new Item.Properties(), Blocks.INSTANCE.getCable().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "cable")
            ))
        )));
    }

    private void registerGridItems() {
        Blocks.INSTANCE.getGrid().forEach((color, block) -> itemRegistry.register(
            Blocks.INSTANCE.getGrid().getId(color, GRID).getPath(),
            () -> new NamedBlockItem(
                block.get(),
                new Item.Properties(),
                Blocks.INSTANCE.getGrid().getName(color, createTranslation(
                    BLOCK_TRANSLATION_CATEGORY,
                    "grid"
                ))
            )
        ));
        Blocks.INSTANCE.getCraftingGrid().forEach((color, block) -> itemRegistry.register(
            Blocks.INSTANCE.getCraftingGrid().getId(color, CRAFTING_GRID).getPath(),
            () -> new NamedBlockItem(
                block.get(),
                new Item.Properties(),
                Blocks.INSTANCE.getCraftingGrid().getName(color, createTranslation(
                    BLOCK_TRANSLATION_CATEGORY,
                    "crafting_grid"
                ))
            )
        ));
    }

    private void registerControllerItems() {
        Blocks.INSTANCE.getController().forEach((c, block) -> Items.INSTANCE.addRegularController(itemRegistry.register(
            Blocks.INSTANCE.getController().getId(c, CONTROLLER).getPath(),
            () -> new ControllerBlockItem(
                block.get(),
                Blocks.INSTANCE.getController().getName(c, createTranslation(
                    BLOCK_TRANSLATION_CATEGORY,
                    "controller"
                ))
            )
        )));
        Blocks.INSTANCE.getCreativeController().forEach((color, block) -> Items.INSTANCE.addController(
            itemRegistry.register(
                Blocks.INSTANCE.getCreativeController().getId(color, CREATIVE_CONTROLLER).getPath(),
                () -> new CreativeControllerBlockItem(
                    block.get(),
                    Blocks.INSTANCE.getCreativeController().getName(color, createTranslation(
                        BLOCK_TRANSLATION_CATEGORY,
                        "creative_controller"
                    ))
                )
            )
        ));
    }

    private void registerDetectorItems() {
        Blocks.INSTANCE.getDetector().forEach((color, block) -> Items.INSTANCE.addDetector(itemRegistry.register(
            Blocks.INSTANCE.getDetector().getId(color, DETECTOR).getPath(),
            () -> new NamedBlockItem(
                block.get(),
                new Item.Properties(),
                Blocks.INSTANCE.getDetector().getName(color, createTranslation(
                    BLOCK_TRANSLATION_CATEGORY,
                    "detector"
                ))
            )
        )));
    }

    private void registerImporterItems() {
        Blocks.INSTANCE.getImporter().forEach((color, block) -> Items.INSTANCE.addImporter(itemRegistry.register(
            Blocks.INSTANCE.getImporter().getId(color, IMPORTER).getPath(),
            () -> new NamedBlockItem(block.get(), new Item.Properties(), Blocks.INSTANCE.getImporter().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "importer")
            ))
        )));
    }

    private void registerExporterItems() {
        Blocks.INSTANCE.getExporter().forEach((color, block) -> Items.INSTANCE.addExporter(itemRegistry.register(
            Blocks.INSTANCE.getExporter().getId(color, EXPORTER).getPath(),
            () -> new NamedBlockItem(block.get(), new Item.Properties(), Blocks.INSTANCE.getExporter().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "exporter")
            ))
        )));
    }

    private void registerExternalStorageItems() {
        Blocks.INSTANCE.getExternalStorage().forEach((color, block) -> Items.INSTANCE.addExternalStorage(
            itemRegistry.register(
                Blocks.INSTANCE.getExternalStorage().getId(color, EXTERNAL_STORAGE).getPath(),
                () -> new NamedBlockItem(block.get(), new Item.Properties(),
                    Blocks.INSTANCE.getExternalStorage().getName(
                        color,
                        createTranslation(BLOCK_TRANSLATION_CATEGORY, "external_storage")
                    )
                )
            )
        ));
    }

    private void registerDestructorItems() {
        Blocks.INSTANCE.getDestructor().forEach((color, block) -> Items.INSTANCE.addDestructor(itemRegistry.register(
            Blocks.INSTANCE.getDestructor().getId(color, DESTRUCTOR).getPath(),
            () -> new NamedBlockItem(block.get(), new Item.Properties(), Blocks.INSTANCE.getDestructor().getName(
                color,
                createTranslation(BLOCK_TRANSLATION_CATEGORY, "destructor")
            ))
        )));
    }

    private void registerStorageItems() {
        for (final ItemStorageType.Variant variant : ItemStorageType.Variant.values()) {
            registerItemStorageItems(variant);
        }
        for (final FluidStorageType.Variant variant : FluidStorageType.Variant.values()) {
            registerFluidStorageItems(variant);
        }
    }

    private void registerItemStorageItems(final ItemStorageType.Variant variant) {
        if (variant != ItemStorageType.Variant.CREATIVE) {
            Items.INSTANCE.setItemStoragePart(variant, itemRegistry.register(
                forItemStoragePart(variant).getPath(),
                SimpleItem::new
            ));
        }
        Items.INSTANCE.setItemStorageDisk(
            variant,
            itemRegistry.register(
                forStorageDisk(variant).getPath(),
                () -> new ItemStorageDiskItem(variant)
            )
        );
        itemRegistry.register(
            forItemStorageBlock(variant).getPath(),
            () -> new ItemStorageBlockBlockItem(
                Blocks.INSTANCE.getItemStorageBlock(variant),
                variant
            )
        );
    }

    private void registerFluidStorageItems(final FluidStorageType.Variant variant) {
        if (variant != FluidStorageType.Variant.CREATIVE) {
            Items.INSTANCE.setFluidStoragePart(variant, itemRegistry.register(
                forFluidStoragePart(variant).getPath(),
                SimpleItem::new
            ));
        }
        Items.INSTANCE.setFluidStorageDisk(
            variant,
            itemRegistry.register(
                forFluidStorageDisk(variant).getPath(),
                () -> new FluidStorageDiskItem(variant)
            )
        );
        itemRegistry.register(
            forFluidStorageBlock(variant).getPath(),
            () -> new FluidStorageBlockBlockItem(
                Blocks.INSTANCE.getFluidStorageBlock(variant),
                variant
            )
        );
    }

    private void registerUpgrades() {
        Items.INSTANCE.setUpgrade(itemRegistry.register(
            UPGRADE.getPath(),
            SimpleItem::new
        ));
        final Supplier<SimpleUpgradeItem> speedUpgrade = itemRegistry.register(
            SPEED_UPGRADE.getPath(),
            () -> new SimpleUpgradeItem(
                PlatformApi.INSTANCE.getUpgradeRegistry(),
                Platform.INSTANCE.getConfig().getUpgrade()::getSpeedUpgradeEnergyUsage,
                false
            )
        );
        Items.INSTANCE.setSpeedUpgrade(speedUpgrade);
        final Supplier<SimpleUpgradeItem> stackUpgrade = itemRegistry.register(
            STACK_UPGRADE.getPath(),
            () -> new SimpleUpgradeItem(
                PlatformApi.INSTANCE.getUpgradeRegistry(),
                Platform.INSTANCE.getConfig().getUpgrade()::getStackUpgradeEnergyUsage,
                false
            )
        );
        Items.INSTANCE.setStackUpgrade(stackUpgrade);
        final Supplier<FortuneUpgradeItem> fortune1Upgrade = itemRegistry.register(
            FORTUNE_1_UPGRADE.getPath(),
            () -> new FortuneUpgradeItem(PlatformApi.INSTANCE.getUpgradeRegistry(), 1)
        );
        Items.INSTANCE.setFortune1Upgrade(fortune1Upgrade);
        final Supplier<FortuneUpgradeItem> fortune2Upgrade = itemRegistry.register(
            FORTUNE_2_UPGRADE.getPath(),
            () -> new FortuneUpgradeItem(PlatformApi.INSTANCE.getUpgradeRegistry(), 2)
        );
        Items.INSTANCE.setFortune2Upgrade(fortune2Upgrade);
        final Supplier<FortuneUpgradeItem> fortune3Upgrade = itemRegistry.register(
            FORTUNE_3_UPGRADE.getPath(),
            () -> new FortuneUpgradeItem(PlatformApi.INSTANCE.getUpgradeRegistry(), 3)
        );
        Items.INSTANCE.setFortune3Upgrade(fortune3Upgrade);
        final Supplier<SimpleUpgradeItem> silkTouchUpgrade = itemRegistry.register(
            SILK_TOUCH_UPGRADE.getPath(),
            () -> new SimpleUpgradeItem(
                PlatformApi.INSTANCE.getUpgradeRegistry(),
                Platform.INSTANCE.getConfig().getUpgrade()::getSilkTouchUpgradeEnergyUsage,
                true
            )
        );
        Items.INSTANCE.setSilkTouchUpgrade(silkTouchUpgrade);
        addApplicableUpgrades();
    }

    private void registerBlockEntities() {
        BlockEntities.INSTANCE.setCable(blockEntityTypeRegistry.register(
            CABLE.getPath(),
            () -> BlockEntityType.Builder.of(
                CableBlockEntity::new,
                Blocks.INSTANCE.getCable().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setController(blockEntityTypeRegistry.register(
            CONTROLLER.getPath(),
            () -> BlockEntityType.Builder.of(
                (pos, state) -> new ControllerBlockEntity(ControllerType.NORMAL, pos, state),
                Blocks.INSTANCE.getController().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setCreativeController(blockEntityTypeRegistry.register(
            CREATIVE_CONTROLLER.getPath(),
            () -> BlockEntityType.Builder.of(
                (pos, state) -> new ControllerBlockEntity(ControllerType.CREATIVE, pos, state),
                Blocks.INSTANCE.getCreativeController().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setDiskDrive(blockEntityTypeRegistry.register(
            DISK_DRIVE.getPath(),
            () -> BlockEntityType.Builder.of(
                ForgeDiskDriveBlockEntity::new,
                Blocks.INSTANCE.getDiskDrive()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setGrid(blockEntityTypeRegistry.register(
            GRID.getPath(),
            () -> BlockEntityType.Builder.of(
                GridBlockEntity::new,
                Blocks.INSTANCE.getGrid().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setCraftingGrid(blockEntityTypeRegistry.register(
            CRAFTING_GRID.getPath(),
            () -> BlockEntityType.Builder.of(
                CraftingGridBlockEntity::new,
                Blocks.INSTANCE.getCraftingGrid().toArray()
            ).build(null)
        ));

        for (final ItemStorageType.Variant variant : ItemStorageType.Variant.values()) {
            BlockEntities.INSTANCE.setItemStorageBlock(variant, blockEntityTypeRegistry.register(
                forItemStorageBlock(variant).getPath(),
                () -> BlockEntityType.Builder.of(
                    (pos, state) -> new ItemStorageBlockBlockEntity(pos, state, variant),
                    Blocks.INSTANCE.getItemStorageBlock(variant)
                ).build(null)
            ));
        }

        for (final FluidStorageType.Variant variant : FluidStorageType.Variant.values()) {
            BlockEntities.INSTANCE.setFluidStorageBlock(variant, blockEntityTypeRegistry.register(
                forFluidStorageBlock(variant).getPath(),
                () -> BlockEntityType.Builder.of(
                    (pos, state) -> new FluidStorageBlockBlockEntity(pos, state, variant),
                    Blocks.INSTANCE.getFluidStorageBlock(variant)
                ).build(null)
            ));
        }

        BlockEntities.INSTANCE.setImporter(blockEntityTypeRegistry.register(
            IMPORTER.getPath(),
            () -> BlockEntityType.Builder.of(
                    ImporterBlockEntity::new,
                    Blocks.INSTANCE.getImporter().toArray())
                .build(null)
        ));
        BlockEntities.INSTANCE.setExporter(blockEntityTypeRegistry.register(
            EXPORTER.getPath(),
            () -> BlockEntityType.Builder.of(
                    ExporterBlockEntity::new,
                    Blocks.INSTANCE.getExporter().toArray())
                .build(null)
        ));
        BlockEntities.INSTANCE.setInterface(blockEntityTypeRegistry.register(
            INTERFACE.getPath(),
            () -> BlockEntityType.Builder.of(InterfaceBlockEntity::new, Blocks.INSTANCE.getInterface()).build(null)
        ));
        BlockEntities.INSTANCE.setExternalStorage(blockEntityTypeRegistry.register(
            EXTERNAL_STORAGE.getPath(),
            () -> BlockEntityType.Builder.of(
                ExternalStorageBlockEntity::new,
                Blocks.INSTANCE.getExternalStorage().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setDetector(blockEntityTypeRegistry.register(
            DETECTOR.getPath(),
            () -> BlockEntityType.Builder.of(
                DetectorBlockEntity::new,
                Blocks.INSTANCE.getDetector().toArray()
            ).build(null)
        ));
        BlockEntities.INSTANCE.setDestructor(blockEntityTypeRegistry.register(
            DESTRUCTOR.getPath(),
            () -> BlockEntityType.Builder.of(
                DestructorBlockEntity::new,
                Blocks.INSTANCE.getDestructor().toArray()
            ).build(null)
        ));

        blockEntityTypeRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerMenus() {
        Menus.INSTANCE.setController(menuTypeRegistry.register(
            CONTROLLER.getPath(),
            () -> IForgeMenuType.create(ControllerContainerMenu::new)
        ));
        Menus.INSTANCE.setDiskDrive(menuTypeRegistry.register(
            DISK_DRIVE.getPath(),
            () -> IForgeMenuType.create(DiskDriveContainerMenu::new)
        ));
        Menus.INSTANCE.setGrid(menuTypeRegistry.register(
            GRID.getPath(),
            () -> IForgeMenuType.create(GridContainerMenu::new)
        ));
        Menus.INSTANCE.setCraftingGrid(menuTypeRegistry.register(
            CRAFTING_GRID.getPath(),
            () -> IForgeMenuType.create(CraftingGridContainerMenu::new)
        ));
        Menus.INSTANCE.setItemStorage(menuTypeRegistry.register(
            ITEM_STORAGE_BLOCK.getPath(),
            () -> IForgeMenuType.create(ItemStorageBlockContainerMenu::new)
        ));
        Menus.INSTANCE.setFluidStorage(menuTypeRegistry.register(
            FLUID_STORAGE_BLOCK.getPath(),
            () -> IForgeMenuType.create(FluidStorageBlockContainerMenu::new)
        ));
        Menus.INSTANCE.setImporter(menuTypeRegistry.register(
            IMPORTER.getPath(),
            () -> IForgeMenuType.create(ImporterContainerMenu::new)
        ));
        Menus.INSTANCE.setExporter(menuTypeRegistry.register(
            EXPORTER.getPath(),
            () -> IForgeMenuType.create(ExporterContainerMenu::new)
        ));
        Menus.INSTANCE.setInterface(menuTypeRegistry.register(
            INTERFACE.getPath(),
            () -> IForgeMenuType.create(InterfaceContainerMenu::new)
        ));
        Menus.INSTANCE.setExternalStorage(menuTypeRegistry.register(
            EXTERNAL_STORAGE.getPath(),
            () -> IForgeMenuType.create(ExternalStorageContainerMenu::new)
        ));
        Menus.INSTANCE.setDetector(menuTypeRegistry.register(
            DETECTOR.getPath(),
            () -> IForgeMenuType.create(DetectorContainerMenu::new)
        ));
        Menus.INSTANCE.setDestructor(menuTypeRegistry.register(
            DESTRUCTOR.getPath(),
            () -> IForgeMenuType.create(DestructorContainerMenu::new)
        ));

        menuTypeRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerSounds() {
        Sounds.INSTANCE.setWrench(soundEventRegistry.register(
            WRENCH.getPath(),
            () -> SoundEvent.createVariableRangeEvent(WRENCH)
        ));

        soundEventRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerRecipeSerializers() {
        recipeSerializerRegistry.register("upgrade_with_enchanted_book", UpgradeWithEnchantedBookRecipeSerializer::new);
        recipeSerializerRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerTickHandler() {
        MinecraftForge.EVENT_BUS.addListener(this::onServerTick);
    }

    @SubscribeEvent
    public void onRegister(final RegisterEvent e) {
        e.register(Registries.LOOT_FUNCTION_TYPE, helper -> {
            // We don't use the helper here as we need the return value.
            final LootItemFunctionType storageBlockLootItemFunction = Registry.register(
                BuiltInRegistries.LOOT_FUNCTION_TYPE,
                STORAGE_BLOCK,
                new LootItemFunctionType(new AbstractStorageBlock.StorageBlockLootItemFunctionSerializer())
            );
            LootFunctions.INSTANCE.setStorageBlock(() -> storageBlockLootItemFunction);
        });
    }

    @SubscribeEvent
    public void onRegisterCreativeModeTab(final CreativeModeTabEvent.Register e) {
        e.registerCreativeModeTab(
            createIdentifier("general"),
            builder -> builder
                .title(createTranslation("itemGroup", "general"))
                .icon(() -> new ItemStack(Blocks.INSTANCE.getController().getDefault()))
                .displayItems((params, output) -> CreativeModeTabItems.append(output::accept))
                .build()
        );
    }

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        final BlockState state = e.getLevel().getBlockState(e.getHitVec().getBlockPos());

        AbstractBaseBlock.tryUseWrench(state, e.getLevel(), e.getHitVec(), e.getEntity(), e.getHand())
            .or(() -> AbstractBaseBlock.tryUpdateColor(
                state,
                e.getLevel(),
                e.getHitVec().getBlockPos(),
                e.getEntity(),
                e.getHand()
            ))
            .ifPresent(result -> {
                e.setCanceled(true);
                e.setCancellationResult(result);
            });
    }

    @SubscribeEvent
    public void registerCapabilities(final AttachCapabilitiesEvent<BlockEntity> e) {
        if (e.getObject() instanceof ControllerBlockEntity controllerBlockEntity) {
            registerControllerEnergy(e, controllerBlockEntity);
        }
        if (e.getObject() instanceof AbstractDiskDriveBlockEntity diskDriveBlockEntity) {
            registerItemHandler(e, diskDriveBlockEntity, AbstractDiskDriveBlockEntity::getDiskInventory);
        }
        if (e.getObject() instanceof InterfaceBlockEntity interfaceBlockEntity) {
            registerItemHandler(e, interfaceBlockEntity, InterfaceBlockEntity::getExportedItems);
        }
    }

    private <T extends BlockEntity> void registerItemHandler(final AttachCapabilitiesEvent<BlockEntity> e,
                                                             final T diskDriveBlockEntity,
                                                             final Function<T, Container> containerSupplier) {
        final LazyOptional<IItemHandler> capability = LazyOptional
            .of(() -> new InvWrapper(containerSupplier.apply(diskDriveBlockEntity)));
        e.addCapability(createIdentifier("items"), new ICapabilityProvider() {
            @Override
            @Nonnull
            public <C> LazyOptional<C> getCapability(final Capability<C> cap,
                                                     @Nullable final Direction side) {
                if (cap == ForgeCapabilities.ITEM_HANDLER) {
                    return capability.cast();
                }
                return LazyOptional.empty();
            }
        });
    }

    private void registerControllerEnergy(final AttachCapabilitiesEvent<BlockEntity> e,
                                          final ControllerBlockEntity controllerBlockEntity) {
        final LazyOptional<IEnergyStorage> capability = LazyOptional
            .of(() -> (IEnergyStorage) controllerBlockEntity.getEnergyStorage());
        e.addCapability(createIdentifier("energy"), new ICapabilityProvider() {
            @Override
            @Nonnull
            public <T> LazyOptional<T> getCapability(final Capability<T> cap,
                                                     @Nullable final Direction side) {
                if (cap == ForgeCapabilities.ENERGY
                    && controllerBlockEntity.getEnergyStorage() instanceof IEnergyStorage) {
                    return capability.cast();
                }
                return LazyOptional.empty();
            }
        });
    }

    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            TickHandler.runQueuedActions();
        }
    }
}
