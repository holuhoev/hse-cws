const path = require("path");


module.exports = {
    entry: ["./src/main/js/app.js"],
    output: {
        path: path.resolve(__dirname, "src"),
        filename: "main/resources/static/built/[name].js"
    },
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