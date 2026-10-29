
A minimal Spring Boot web demo.

## Requirements

- JDK 21 (Spring Boot 4.x requires Java 17+; this project targets Java 21)

## Run

```bash
# Windows (Git Bash / cmd), point JAVA_HOME at JDK 21 first:
export JAVA_HOME="C:/Java/jdk-21.0.5"
mvn spring-boot:run
```

Or build a runnable jar and start it:

```bash
mvn -DskipTests package
java -jar target/ai.dev-java-demo-0.0.1-SNAPSHOT.jar
```

Then open <http://localhost:8091/> — it returns `Hello, ai-dev-java-demo!`.

## 商品资料接口（MyBatis-Plus）

| Method | Path            | 说明                       |
| ------ | --------------- | -------------------------- |
| GET    | /product/list   | 查询列表（可按品牌过滤）   |
| GET    | /product/{id}   | 查询单个                   |
| POST   | /product        | 新增 / 保存                |
| PUT    | /product/{id}   | 修改                       |
| DELETE | /product/{id}   | 删除                       |

`GET /product/list` 不传参数时返回全部商品；传入 `brandId` 时只返回对应品牌的商品：

```bash
curl "http://localhost:8091/product/list?brandId=1"
```

示例（新增，`brandId` 为可选字段）：

```bash
curl -X POST http://localhost:8091/product \
  -H "Content-Type: application/json" \
  -d '{"name":"商品","code":"SKU-1001","brandId":1,"price":12.5,"stock":10}'
```

## 数据库

默认使用 **H2 内存库（MySQL 兼容模式）**，启动时自动建表并写入两条示例数据，开箱即用；
H2 控制台地址 <http://localhost:8091/h2-console>（JDBC URL 填 `jdbc:h2:mem:demo`，用户 `sa`，密码留空）。

切换到真实 MySQL：修改 `src/main/resources/application.yaml` 中的 `spring.datasource`（已给出注释示例），
并建库建表（表结构见 `src/main/resources/schema.sql`）。

> Note: the machine's default `JAVA_HOME` currently points to JDK 8, which cannot
> build/run this project (Spring Boot 4 classes are Java 17 bytecode). Use JDK 21
> (installed at `C:\Java\jdk-21.0.5`) as shown above, or select it in your IDE.
