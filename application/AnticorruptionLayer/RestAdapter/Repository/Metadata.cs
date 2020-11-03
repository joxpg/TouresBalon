using Amazon.DynamoDBv2;
using Amazon.DynamoDBv2.Model;
using DomainModel.Dto;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Repository
{
    /// <summary>
    /// Obtiene y establece la información de metadata en la db No Sql
    /// </summary>
    public class Metadata
    {
        private readonly IConfiguration _configuration;
        private readonly IAmazonDynamoDB _dynamoDBClient;

        public Metadata(IConfiguration configuration, IAmazonDynamoDB dynamoDBClient)
        {
            _configuration = configuration;
            _dynamoDBClient = dynamoDBClient;
        }

        public Metadata(IConfiguration configuration)
        {
            _configuration = configuration;
        }
        public void GetMetadata(InformationProvider infoProvider)
        {
             
            var infos = _configuration["MetadataProvider:InformationProvider"];
            var info = _configuration["AllowedHosts"];
        }

        public void CreateDynamoDbTable()
        {
            try
            {
                CreateTable().GetAwaiter();
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }


        public async Task CreateTable() 
        {
            string tableName = "MetadataRest";
            var request = new CreateTableRequest
            {
                AttributeDefinitions = new List<AttributeDefinition>
                {
                    new AttributeDefinition
                    {
                        AttributeName = "Id",
                        AttributeType = "N"
                    },
                    new AttributeDefinition
                    {
                        AttributeName = "Name",
                        AttributeType = "N"
                    }
                },
                KeySchema = new List<KeySchemaElement>
                {
                    new KeySchemaElement
                    {
                        AttributeName = "Id",
                        KeyType = "HASH"//Partition Key
                    },
                    new KeySchemaElement
                    {
                        AttributeName = "Name",
                        KeyType = "HASH"
                    }
                },
                ProvisionedThroughput = new ProvisionedThroughput
                {
                    ReadCapacityUnits = 5,
                    WriteCapacityUnits = 5
                },
                TableName = tableName
            };

            var response = await _dynamoDBClient.CreateTableAsync(request);            

        }
    }
}
