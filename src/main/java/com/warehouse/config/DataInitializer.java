package com.warehouse.config;

import com.warehouse.entity.Item;
import com.warehouse.entity.Variant;
import com.warehouse.entity.Stock;
import com.warehouse.entity.Price;
import com.warehouse.repo.ItemRepository;
import com.warehouse.repo.VariantRepository;
import com.warehouse.repo.StockRepository;
import com.warehouse.repo.PriceRepository;
import com.warehouse.util.StockType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final VariantRepository variantRepository;
    private final StockRepository stockRepository;
    private final PriceRepository priceRepository;

    public DataInitializer(ItemRepository itemRepository,
                           VariantRepository variantRepository,
                           StockRepository stockRepository,
                           PriceRepository priceRepository) {
        this.itemRepository = itemRepository;
        this.variantRepository = variantRepository;
        this.stockRepository = stockRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) {
        if (itemRepository.count() > 0) return;

        Item tshirt = new Item();
        tshirt.setSku("TSHIRT-001");
        tshirt.setName("T-Shirt");
        tshirt.setDescription("Kaos katun polos unisex");
        tshirt.setCreatedBy("system");
        tshirt.setCreatedDatetime(LocalDateTime.now());

        Variant tshirtSmall = new Variant();
        tshirtSmall.setSize("Small");
        tshirtSmall.setItem(tshirt);

        Variant tshirtMedium = new Variant();
        tshirtMedium.setSize("Medium");
        tshirtMedium.setItem(tshirt);

        tshirt.setVariants(List.of(tshirtSmall, tshirtMedium));
        itemRepository.save(tshirt);

        Stock stockSmall = new Stock();
        stockSmall.setVariant(tshirtSmall);
        stockSmall.setQuantityChange(50);
        stockSmall.setType(StockType.INBOUND.name());
        stockSmall.setReference("Initial Stock");

        Stock stockMedium = new Stock();
        stockMedium.setVariant(tshirtMedium);
        stockMedium.setQuantityChange(30);
        stockMedium.setType(StockType.INBOUND.name());
        stockMedium.setReference("Initial Stock");

        stockRepository.saveAll(List.of(stockSmall, stockMedium));

        Price priceSmall = new Price();
        priceSmall.setVariant(tshirtSmall);
        priceSmall.setPrice(100_000.0);
        priceSmall.setStartDate(LocalDate.now().minusDays(1).atStartOfDay());

        Price priceMedium = new Price();
        priceMedium.setVariant(tshirtMedium);
        priceMedium.setPrice(120_000.0);
        priceMedium.setStartDate(LocalDate.now().minusDays(1).atStartOfDay());

        priceRepository.saveAll(List.of(priceSmall, priceMedium));

    }
}
