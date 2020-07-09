import pandas as pd
import warnings
warnings.filterwarnings('ignore')
import json
import os

testc = {
  "title": {
    "text": "",
    "subtext": ""
  },
  "tooltip": {
    "trigger": "axis"
  },
  "legend": {
    "left": "right",
    "data": [
      "平均气温"
    ]
  },
  "toolbox": {
    "show": False,
    "feature": {
      "dataZoom": {
        "yAxisIndex": "none"
      },
      "dataView": {
        "readOnly": False
      },
      "magicType": {
        "type": [
          "line",
          "bar"
        ]
      },
      "restore": {},
      "saveAsImage": {}
    }
  },
  "xAxis": {
    "type": "category",
    "boundaryGap": False,
    "data": [
      "周一",
      "周二",
      "周三",
      "周四",
      "周五",
      "周六",
      "周日"
    ]
  },
  "yAxis": {
    "type": "value"
  },
  "series": [
    {
      "name": "最高气温",
      "type": "line",
      "data": [
        11,
        11,
        15,
        13,
        12,
        13,
        10
      ],
      "markPoint": {
        "data": [
          {
            "type": "max",
            "name": "最大值"
          },
          {
            "type": "min",
            "name": "最小值"
          }
        ]
      },
      "markLine": {
        "data": [
          {
            "type": "average",
            "name": "平均值"
          }
        ]
      }
    }
  ]
}

basedir = os.path.abspath(os.path.dirname(__file__))
file1='广州预测数据（周）\outcome平均气温（广州）.csv' # 打开的文件
filesave='广州预测数据（周）\outcome_guanzghou_week_ave.json' # 保存的文件
x=testc["xAxis"]
datec=pd.read_csv(file1)['date']
date=list(datec)
# print(date)
x['data']=date
tavec=pd.read_csv(file1)['tave']
tave=list(tavec)
testc["series"][0]['data']=tave
jsontestc = json.dumps(testc)
print(jsontestc)
with open(filesave, 'w') as f:
  json.dump(testc, f)
