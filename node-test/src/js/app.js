var http = require('http');
var fs = require('fs');
var PORT = 1234;

var app = http.createServer(function (req, res) {
    // res.writeHead(200, {'Content-Type': 'text/html'})
    // res.write('<h1>Hello</h1>');
    // res.end();
    var path = __dirname + req.url;
    fs.readFile(path, function (err, data) {
        if (err) {
            res.end();
            return;
        }
        res.write(data.toString());
        res.end();
    })
});
app.listen(PORT, function () {
    console.log('server is running at %d', PORT);

})