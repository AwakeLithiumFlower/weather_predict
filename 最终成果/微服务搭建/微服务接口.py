#
import os
from flask import Flask, render_template, send_file, send_from_directory, json, jsonify, Response, make_response

#
app = Flask(__name__)

@app.route('/show/<string:filename>/', methods=['GET'])

def show(filename):
    # image_data = open("E:/pycharm/app/static/file/"+filename+".jpg", "rb").read()
    # response = make_response(image_data)
    # response.headers['Content-Type'] = 'image/jpeg'
    # return response
    return render_template('upload.html', username=filename)

@app.route('/read_json/<json_name>/', methods=['GET'])

def read_json(json_name):
    filename = json_name + '.json'
    directory = "H:/learn/小学期"  # json文件所在的目录路径

    try:
        with open(directory + '/' + filename) as f:
            # 获得了json文件，下面通过loadf来形成一个列表，其中列表的每个元素都是字典
            jsonStr = json.load(f)
            print(jsonStr)
            print(1)
            # 自己建造字典
            info = dict()
            # 尝试改变数据
            # 如果这里jsonStr导入的不是列表而是字典 就不需要两个[][]了 就可以只用一个[]快速定位到所需的键值
            # 直接输入数据
            info['lesson'] = "lesson"
            info['score'] = "score"
            l = ['iplaypython', [1, 2, 3], {'name':'xiaoming'}]
            # 发现dumps的用法不仅能将字典转换成json格式的显示，还能将列表转化成json格式的显示
            # 如果下面是json.dumps(l)的话，网页输出就是["iplaypython", [1, 2, 3], {"name": "xiaoming"}]
            return json.dumps(jsonStr)
            # 输出是{"lesson": "lesson", "name": 2620, "score": "score"}

    except Exception as e:
        print("error")
        return jsonify({"code": "异常", "message": "{}".format(e)})

if __name__ == '__main__':

    app.run(debug=True, host='127.0.0.1', port=8081)
