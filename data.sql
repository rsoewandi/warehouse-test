-- ========================
--  ITEM
-- ========================
INSERT INTO item (id, sku, name, description, created_by, created_datetime)
VALUES
  (1, 'TSHIRT-001', 'T-Shirt', 'Kaos katun polos unisex', 'system', CURRENT_TIMESTAMP());

-- ========================
--  VARIANT
-- ========================
INSERT INTO variant (id, size, item_id)
VALUES
  (1, 'Small', 1),
  (2, 'Medium', 1);

-- ========================
--  STOCK
-- ========================
INSERT INTO stock (id, variant_id, quantity_change, type, reference)
VALUES
  (1, 1, 50, 'INBOUND', 'Initial Stock'),
  (2, 2, 30, 'INBOUND', 'Initial Stock');

-- ========================
--  PRICE
-- ========================
INSERT INTO price (id, variant_id, price, start_date)
VALUES
  (1, 1, 100000.0, CURRENT_TIMESTAMP()),
  (2, 2, 120000.0, CURRENT_TIMESTAMP());
