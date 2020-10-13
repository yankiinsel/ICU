import { Request, Response } from 'express';

const routes = (app: any) => {
  app.route('/users').get((req: Request, res: Response) => (
    res.send({ admin: 'alan' })));
};

export default routes;
