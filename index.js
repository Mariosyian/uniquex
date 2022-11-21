const bodyParser = require('body-parser');
const express = require('express');
const fileUpload = require('express-fileupload');
const sorts = require('./sorts');

const app = express();
app.set('view engine', 'ejs');

app.use('/static', express.static(`${__dirname}/views/`));
app.use(bodyParser.urlencoded({extended: true}));
app.use(fileUpload());

const port = process.env.PORT || 3000;
const homeURI = process.env.HOME_URI || `http://localhost:${port}/`;

const sortingAlgorithms = new Map();
sortingAlgorithms.set('bubble', () => {});
sortingAlgorithms.set('heap', () => {});
sortingAlgorithms.set('merge', () => {});

app.get('/', (req, res) => {
  logResponse(res);
  res.render(
      'templates/index',
      {algorithms: Array.from(sortingAlgorithms.keys())},
  );
});

app.post('/sort', (req, res) => {
  logResponse(res);
  if (req.files == null || Object.keys(req.files).length === 0) {
    res.render(
        'templates/error',
        {message: 'No file was provided!', status: 400},
    );
  }
  if (req.files.data == null) {
    res.render('templates/error', {message: 'Invalid file name!', status: 400});
  }
  if (
    req.body.sorting == null ||
    req.body.sorting === '' ||
    !Array.from(sortingAlgorithms.keys()).includes(req.body.sorting)
  ) {
    res.render(
        'templates/error',
        {message: 'Invalid sorting algorithm!', status: 400},
    );
  }

  const text = req.files.data.data.toString('utf-8');
  const algorithm = req.body.sorting;

  console.log(text);
  // TODO: Parse file data and apply sorting algorithm. Break down into (id, student name, grade)???
  res.redirect('/');
});

app.listen(port, () => {
  console.log(`Server listening at: ${homeURI} on port: ${port}`);
});

/** *** UTILITY METHODS *****/
/**
 * Logs the type of request made to an endpoint and its status code.
 * @param {Response} res
 */
function logResponse(res) {
  console.log(
      res.req.method + ' @ ' +
        res.req.url + ' with response: ' +
        res.statusCode,
  );
}