import {
  ApolloClient,
  gql,
  NormalizedCacheObject
} from '@apollo/client';
import { cache } from './cache';

const client: ApolloClient<NormalizedCacheObject> = new ApolloClient({
  cache,
  uri: 'http://localhost:4000/graphql'
});

client
  .query({
    query: gql`
      query TestQuery {
        launch(id: 56) {
          id
          site
          mission {
            name
          }
        }
      }
    `
  })
  .then(result => console.log(result));
