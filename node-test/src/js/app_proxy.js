var http = require('http');

var httpProxy = require('http-proxy');

var PORT = 1234;

var proxy = httpProxy.createProxyServer();
proxy.on('error', function (err, req, res) {
    res.end();// 输出空白响应数据
});

var app = http.createServer(function (req, res) {
    // 执行反向代理
    proxy.web(req, res, {
        target: 'http://localhost:8080' // 目标地址
    });
});

app.listen(PORT, function () {
    console.log('server is running at %d', PORT);
app})