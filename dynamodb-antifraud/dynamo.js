#!/usr/bin/env node
process.env.AWS_PROFILE = "dev"
const AWS = require("aws-sdk");
const { program } = require('commander');
const { createTable, deleteTable, describeTable } = require('./scripts/table-operations')
const { createItem, createItemBatch } = require('./scripts/write-operations')
const { listItems } = require('./scripts/read-operations')

AWS.config.update({ region: 'sa-east-1' });
global.dynamodb = new AWS.DynamoDB();
global.docClient = new AWS.DynamoDB.DocumentClient();
global.tableName = 'dynamodb-antifraud.antifraud_aggregator.transactions'

program
  .command('table')
  .option('--create', 'Create table')
  .option('--delete', 'Delete table')
  .option('--describe', 'Describe table')
  .action((cmd) => {
    if (cmd.create) return createTable()
    if (cmd.delete) return deleteTable()
    if (cmd.describe) return describeTable()
  })

program
  .command('item')
  .option('--create', 'Create item')
  .option('--create-batch', 'Create items in batch')
  .option('--list', 'List items')
  .action((cmd) => {
    if (cmd.create) return createItem()
    if (cmd.createBatch) return createItemBatch()
    if (cmd.list) return listItems()
  })

program.parse(process.argv);
