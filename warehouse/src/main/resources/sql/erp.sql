-- 物料种类管理
DROP TABLE IF EXISTS `erp_base`.`material_kind_manage`;
CREATE TABLE `erp_base`.`material_kind_manage` (
	`product_kind_id` INT ( 8 ) auto_increment COMMENT '产品编号',
	`product_kind_name` VARCHAR ( 32 ) COMMENT '物料名称',
	`create_time` datetime COMMENT '创建时间',
	`purchase_price` DECIMAL ( 12, 2 ) COMMENT '物料进价',
	`selling_price` DECIMAL ( 12, 2 ) COMMENT '物料售价',
	`unit` INT ( 8 ) COMMENT '售货单位(0：块、1：个、2：米、3：平方)',
	`update_time` datetime COMMENT '修改时间',
	`consumer_id` INT ( 8 ) COMMENT '客户编号',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
	PRIMARY KEY ( `product_kind_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '物料种类管理';

-- 仓库库存管理
DROP TABLE IF EXISTS `erp_base`.`warehouse_inventory_manage`;
CREATE TABLE `erp_base`.`warehouse_inventory_manage` (
	`stock_id` INT ( 8 ) auto_increment COMMENT '库存编号',
	`product_kind_id` VARCHAR ( 32 ) COMMENT '物料类型编号',
	`stock_num` INT ( 8 ) COMMENT '库存数量',
	`total_amount` DECIMAL ( 12, 2 ) COMMENT '总价',
	`create_time` datetime COMMENT '创建时间',
	`update_time` datetime COMMENT '更新时间',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
	PRIMARY KEY ( `stock_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '仓库库存管理';

-- 进售货记录
DROP TABLE IF EXISTS `erp_base`.`warehouse_purchase_selling_record`;
CREATE TABLE `erp_base`.`warehouse_purchase_selling_record` (
	`record_id` INT ( 8 ) auto_increment COMMENT '库存编号',
	`order_id` INT ( 8 ) COMMENT '订单编号',
	`product_kind_id` INT ( 8 ) COMMENT '物料类型编号',
	`stock_num` DECIMAL ( 12, 2 ) COMMENT '库存数量',
	`manage_type` INT ( 8 ) COMMENT '仓管类型 0：退还厂方、1：进货、2：售货、3：退货',
	`purchase_price` DECIMAL ( 12, 2 ) COMMENT '物料进价',
	`selling_price` DECIMAL ( 12, 2 ) COMMENT '物料售价',
	`consumer_id` INT ( 8 ) COMMENT '客户编号',
	`create_time` datetime COMMENT '创建时间',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
PRIMARY KEY ( `record_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '进售货记录';
-- 客户管理
DROP TABLE IF EXISTS `erp_base`.`consumer_manage_record`;
CREATE TABLE `erp_base`.`consumer_manage_record` (
	`consumer_id` INT ( 8 ) auto_increment COMMENT '客户编号',
	`consumer_name` VARCHAR ( 32 ) COMMENT '客户名称',
	`consumer_type` INT ( 8 ) COMMENT '客户类型 0：厂方、1：客户',
	`contact_phone` varchar ( 32 ) COMMENT '联系方式',
	`contact_addr` VARCHAR ( 32 ) COMMENT '联系地址',
	`create_time` datetime COMMENT '创建时间',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
PRIMARY KEY ( `consumer_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '客户管理';

-- 订单管理
DROP TABLE IF EXISTS `erp_base`.`purchase_selling_order_record`;
CREATE TABLE `erp_base`.`purchase_selling_order_record` (
	`order_id` INT ( 8 ) auto_increment COMMENT '订单编号',
	`trade_type` VARCHAR ( 32 ) COMMENT '交易状态 已下单、已发货、已付款',
	`manage_type` VARCHAR ( 32 ) COMMENT '0：退还厂方、1：进货、2：售货、3：退货',
	`product_kind_id` INT ( 8 ) COMMENT '物料类型编号',
	`stock_num` DECIMAL ( 12, 2 ) COMMENT '物料数量',
	`unit_price` DECIMAL ( 12, 2 ) COMMENT '单价',
	`discount` DECIMAL ( 12, 2 ) COMMENT '折扣 单位%',
	`discount_amount` DECIMAL ( 12, 2 ) COMMENT '订单折扣金额 单价*数量*折扣',
	`purchase_price` DECIMAL ( 12, 2 ) COMMENT '物料进价',
	`total_amount` DECIMAL ( 12, 2 ) COMMENT '订单总金额',
	`net_receipt` DECIMAL ( 12, 2 ) COMMENT '订单利润',
	`consumer_id` INT ( 8 ) COMMENT '客户编号',
	`pay_type` INT ( 8 ) COMMENT '付款方式 银行卡转账、现金、支付宝支付、微信支付',
	`create_time` datetime COMMENT '创建时间',
	`trade_time` datetime COMMENT '交易时间',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
	PRIMARY KEY ( `order_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '订单管理';

-- 财务流水
DROP TABLE IF EXISTS `erp_base`.`finance_flow_record`;
CREATE TABLE `erp_base`.`finance_flow_record` (
	`flow_id` INT ( 8 ) auto_increment COMMENT '流水ID',
	`flow_number` VARCHAR ( 32 ) COMMENT '流水号',
	`order_id` INT ( 8 ) COMMENT '订单ID',
	`product_kind_id` INT ( 8 ) COMMENT '物料类型编号',
	`flow_amount` DECIMAL ( 12, 2 ) COMMENT '流水金额',
	`flow_type` INT ( 8 ) COMMENT '流水类型 0：退还厂方、1：进货、2：售货、3：退货',
	`flow_time` datetime COMMENT '流水时间',
	`pay_type` INT ( 8 ) COMMENT '付款方式 银行卡转账、现金、支付宝支付、微信支付',
	`consumer_id` INT ( 8 ) COMMENT '客户编号',
	`remark` VARCHAR ( 64 ) COMMENT '备注',
PRIMARY KEY ( `flow_id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '财务流水';

-- 库存
DROP VIEW IF EXISTS `v_warehouse_inventory`;
CREATE VIEW `v_warehouse_inventory` AS
SELECT
	inventory.stock_id,
	inventory.product_kind_id,
	inventory.stock_num,
	inventory.total_amount,
	inventory.create_time,
	inventory.update_time,
	consumer.consumer_id,
	consumer.consumer_name,
	consumer.consumer_type,
	consumer.contact_phone,
	consumer.contact_addr,
	kind.product_kind_name,
	kind.unit 
FROM
	warehouse_inventory_manage inventory
	JOIN material_kind_manage kind ON inventory.product_kind_id = kind.product_kind_id
	JOIN consumer_manage_record consumer ON kind.consumer_id = consumer.consumer_id;
	
-- 订单
DROP VIEW IF EXISTS `v_warehouse_order`;
CREATE VIEW `v_warehouse_order` AS SELECT
record.order_id,
record.trade_type,
record.manage_type,
record.product_kind_id,
kind.product_kind_name,
kind.unit,
record.stock_num,
record.unit_price,
record.discount,
record.discount_amount,
record.purchase_price,
record.total_amount,
record.net_receipt,
record.create_time,
consumer.consumer_name,
consumer.consumer_id 
FROM
	purchase_selling_order_record record
	JOIN consumer_manage_record consumer ON record.consumer_id = consumer.consumer_id
	JOIN material_kind_manage kind ON record.product_kind_id = kind.product_kind_id;
	
-- 财务
DROP VIEW IF EXISTS `v_finance_flow`;
CREATE VIEW `v_finance_flow` AS 
SELECT
record.order_id,
record.flow_id,
record.flow_number,
record.product_kind_id,
record.flow_amount,
record.flow_type,
record.flow_time,
record.consumer_id,
consumer.consumer_name,
kind.product_kind_name
FROM
	finance_flow_record record
	JOIN consumer_manage_record consumer ON record.consumer_id = consumer.consumer_id
	JOIN material_kind_manage kind ON record.product_kind_id = kind.product_kind_id;
	
-- 进售货记录
DROP VIEW IF EXISTS `v_purchase_selling_record`;
CREATE VIEW `v_purchase_selling_record` AS 
SELECT
	record.record_id,
	record.order_id,
	record.stock_num,
	record.product_kind_id,
	record.manage_type,
	record.purchase_price,
	record.selling_price,
	record.consumer_id,
	record.create_time,
	consumer.consumer_name,
	kind.product_kind_name 
FROM
	warehouse_purchase_selling_record record
	JOIN consumer_manage_record consumer ON record.consumer_id = consumer.consumer_id
	JOIN material_kind_manage kind ON record.product_kind_id = kind.product_kind_id;
	