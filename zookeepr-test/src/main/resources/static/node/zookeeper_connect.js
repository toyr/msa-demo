var zookeeper = require('node-zookeeper-client');

var CONNECTION_STRING = '127.0.0.1:2181';
var OPTIONS = {
    seesionTimeout: 5000
};

var zk = zookeeper.createClient(CONNECTION_STRING, OPTIONS);
zk.on('connected', function () {
    console.log(zk);
    zk.close();
})

// 获取
zk.getChildren('/', function (error, children, stat) {
    if (error) {
        console.log(error.stack);
        return;
    }
    console.log(children);
});

// 是否存在
zk.exists('/foo', function (error, stat) {
    if (stat) {
        console.log('node exists');
    } else {
        console.log('node does not exist');
    }

});

// 创建节点
zk.create('/foo', new Buffer('hello'), function (error, path) {
    console.log(path);
});

// 获取数据
zk.getData('/foo', function (error, data, stat) {
    console.log(data.toString());
});

// 更新数据
zk.setData('/foo', new Buffer('hello'), function (error, stat) {
    console.log(stat);
});

// 删除节点
zk.remove('/foo', function (error) {
    if (!error) {
        console.log('node is deleted');
    }
});