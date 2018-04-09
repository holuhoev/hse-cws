const webpack = require('webpack');
const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const path = require('path');
const WriteFilePlugin = require('write-file-webpack-plugin');
const webAppPath = '../src/main/js/';


module.exports = merge(common, {
    devtool: 'eval',
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new WriteFilePlugin({
            test: /^(?!.*(hot)).*/,
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, webAppPath),
        stats: 'errors-only',
        hot: true,
        inline: true,
        watchOptions: {
            poll: true
        }
    }
});