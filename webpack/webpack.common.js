const webpack = require('webpack');
const path = require('path');
const webAppPath = '../src/main/js/';

const babelSettings = {
    extends: path.join(__dirname, '../.babelrc')
};

module.exports = {
    entry: ['babel-polyfill', path.join(__dirname, webAppPath + 'index.js')],
    output: {
        path: path.join(__dirname, '../target/classes/static/built/'),
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, webAppPath),
                exclude: /node_modules/,
                loader: 'babel-loader?' + JSON.stringify(babelSettings),
                query: {
                    cacheDirectory: true
                }
            },
            {
                test: /\.less$/,
                use: [{
                    loader: "style-loader" // creates style nodes from JS strings
                }, {
                    loader: "css-loader" // translates CSS into CommonJS
                }, {
                    loader: "less-loader" // compiles Less to CSS
                }]
            },
            {
                test: /\.(ttf|eot|png|svg|woff(2)?)(\?[a-z0-9=&.]+)?$/,
                loader: 'url-loader'
            },
            {
                test: /\.css$/,
                loader: "style-loader!css-loader"
            }
        ]
    }
};