const { ApolloServer } = require('apollo-server');
const typeDefs = require('./schema');
const resolvers = require('./resolvers')
const { createStore } = require('./utils')

const LaunchAPI = require('./datasources/launch')
const UserAPI = require('./datasources/user')

const store = createStore();

const server = new ApolloServer({
   typeDefs,
   resolvers,
   dataSources: () => ({
     launchAPI: new LaunchAPI(),
     userAPI: new UserAPI( { store })
   }) 
});

server.listen().then(() => {
  console.log(`
    Server is running!
    Listening on port 4000
    Explore at https://studio.apollographql.com/dev
  `);
});
