const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const DIST = process.env.DIST || 'src/main/resources/META-INF/resources';

module.exports = {
    entry: {
        main: './src/main/web/main.js'
    },

    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            inject: 'head',
            favicon: './src/main/web/favicon.ico',
            template: './src/main/web/index.html'
        }),
        new MiniCssExtractPlugin({
            filename: 'styles.css',
            chunkFilename: '[id].css'
        })
    ],

    module: {
        rules: [
            {
                test: /\.(png|jpg|gif)$/i,
                use: [{
                    loader: 'url-loader',
                    options: {
                        limit: 8192
                    }
                }]
            },
            {test: /\.svg$/, use: 'file-loader'},
            {test: /\.ico$/, loader: 'file-loader?name=[name].[ext]'},
            {
                test: /\.(woff(2)?|ttf|eot)$/,
                use: [{
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]',
                        outputPath: 'webfonts/'
                    }
                }]
            },
            {
                test: /\.css$/,
                use: [
                    {loader: MiniCssExtractPlugin.loader},
                    {loader: 'css-loader'},
                    {loader: 'postcss-loader'}
                ]
            },
            {
                test: /\.scss$/,
                use: [
                    {loader: MiniCssExtractPlugin.loader},
                    {loader: 'css-loader'},
                    {loader: 'postcss-loader'},
                    {
                        loader: 'sass-loader',
                        options: {
                            includePaths: [
                                './node_modules/@patternfly/patternfly/'
                            ]
                        }
                    }]
            }
        ]
    },

    output: {
        path: path.resolve(__dirname, DIST),
        filename: '[name].js'
    }
}
