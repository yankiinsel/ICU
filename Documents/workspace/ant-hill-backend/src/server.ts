import * as express from 'express';
import { createServer, Server } from 'http';
import * as cors from 'cors';
import * as path from 'path';
import * as bodyParser from 'body-parser';

import routes from './api/routes';
import config from './config';

// Constants
const DEFAULT_PORT = 8080;
const app = express();
const port: string | number = process.env.PORT || DEFAULT_PORT;

// parse application/x-www-form-urlencoded body
app.use(bodyParser.urlencoded({ extended: false }));
// parse application/json body
app.use(bodyParser.json());
app.use(cors());
app.options('*', cors());
app.use(express.static(path.join(__dirname, '../public')));

const server: Server = createServer(app);

server.listen(port, () => {
  console.log('Running server on port %s', port);
});

routes(app);

module.exports = config;
