const path = require("path");
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
    entry: ["./src/main/js/index.js"],
    output: {
        path: path.resolve(__dirname, "src"),
        filename: "main/resources/static/built/bundle.js"
    },
    devtool: 'inline-source-map',
    plugins: [
        new CleanWebpackPlugin(['src/main/resources/static/built'])
    ],
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            }
        ]
    }
};