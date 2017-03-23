DROP TABLE Greeting IF EXISTS;

CREATE TABLE Greeting (
  stock_id BIGINT NOT NULL,
  stock_name VARCHAR(100) NOT NULL,
  stock_market VARCHAR(100) NOT NULL,
  stock_price VARCHAR(100) NOT NULL,
  last_update VARCHAR(100) NOT NULL,
  PRIMARY KEY(stock_id)
);

