import os
import sys
from sys import argv
from flask import Flask, render_template, send_file, send_from_directory, json, jsonify, Response, make_response, \
    url_for, request

# from flask_script import Manager
from pip._vendor import requests

basedir = os.path.abspath(os.path.dirname(__file__))
app = Flask(__name__)


@app.route('/read_json', methods=['GET'])
def read_json():
    json_name = 'outcome12'
    filename = json_name + '.json'

    directory = "C:\\Users\\Aro\\Desktop\\new"

    with open(directory + '/' + filename) as f:
        jsonStr = json.load(f)
    jsonStr['0']['0'] = 90
    # modify
    del jsonStr['0']['1']
    # delete
    print("script_name:" + argv[0]+"\r")

    print(json.dumps(jsonStr)+"\r")
    return '成功，请返回java端查看获得的数据888'


if __name__ == '__main__':
    app.run(debug=False, host='127.0.0.1', port=8008)
