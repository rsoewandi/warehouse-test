# Warehouse Management

Backend system for managing a warehouse.  
Built with **Java 21* and **Spring Boot 3.2.2**, using **H2 in-memory database** and **Swagger UI** to test APIs easily.

---

## How to Run

### Requirements
- Java 21
- Maven (or use `mvnw` wrapper)

### Steps

#### 1. Clone this repository
```bash
git clone <your-repo-url>
cd <your-repo-folder>
```
#### 2. Run the application
```bash
mvn spring-boot:run
```

## API Flow

#### 1. Add Item
POST /api/items

```bash
{
  "sku": "TSHIRT-001",
  "name": "T-Shirt",
  "description": "Cotton T-Shirt",
  "userName": "testing01"
}
```

#### 2. Add Variant

POST /api/variants/{itemId}

```bash
{
  "code": "TSHIRT-001-BLK-M",
  "color": "Black",
  "size": "M",
  "price": 150000
}
```

#### 3. Sale (Reduce Stock)

POST /api/stocks/add

```bash
{
  "variantId": 1,
  "quantity": 3,
  "remarks": "Customer order"
}
```

#### 4. Add Variant

POST /api/stocks/reduce

```bash
{
  "code": "TSHIRT-001-BLK-M",
  "color": "Black",
  "size": "M",
  "price": 150000
}
```

#### 5. Check Stock

GET  /api/variants/{id}



#### Flow Summary
Item → Variant → Inbound → Sale → Stock Update
