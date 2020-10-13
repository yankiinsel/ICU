import * as dotenv from 'dotenv';

dotenv.config();

const config = {
  DB_USERNAME: process.env.DB_USERNAME,
  DB_PASSWORD: process.env.DB_PASSWORD,
  DB_NAME: process.env.DB_NAME,
  DB_COLLECTION_URL: process.env.DB_COLLECTION_URL,
};

export default config;
