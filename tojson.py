import pandas as pd
import time
from itertools import product
from joblib import Parallel,delayed
import numpy as np
import warnings
warnings.filterwarnings('ignore')
from warnings import catch_warnings,filterwarnings
from dateutil.relativedelta import relativedelta
import json
import statsmodels as sm
from statsmodels.graphics import tsaplots as tsa
from datetime import datetime
from statsmodels.tsa.stattools import adfuller
import matplotlib.pylab as plt
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima_model import ARIMA
from statsmodels.tsa.statespace.sarimax import SARIMAX
from statsmodels.tsa.seasonal import seasonal_decompose

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
      "最高气温",
      "最低气温"
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
    "type": "value",
    "axisLabel": {
      "formatter": "{value} °C"
    }
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
    },
    {
      "name": "最低气温",
      "type": "line",
      "data": [
        1,
        -2,
        2,
        5,
        3,
        2,
        0
      ],
      "markPoint": {
        "data": [
          {
            "name": "周最低",
            "value": -2,
            "xAxis": 1,
            "yAxis": -1.5
          }
        ]
      },
      "markLine": {
        "data": [
          {
            "type": "average",
            "name": "平均值"
          },
          [
            {
              "symbol": "none",
              "x": "90%",
              "yAxis": "max"
            },
            {
              "symbol": "circle",
              "label": {
                "normal": {
                  "position": "start",
                  "formatter": "最大值"
                }
              },
              "type": "max",
              "name": "最高点"
            }
          ]
        ]
      }
    }
  ]
}

file='maxmin.csv' # 打开的文件
x=testc["xAxis"]
datec=pd.read_csv(file)['date']
date=list(datec)
# print(date)
x['data']=date
tmaxc=pd.read_csv(file)['tmax']
tmax=list(tmaxc)
testc["series"][0]['data']=tmax
tminc=pd.read_csv(file)['tmin']
tmin=list(tminc)
testc["series"][1]['data']=tmin
jsontestc = json.dumps(testc)
print(jsontestc)
with open('outcometest.json', 'w') as f:
  json.dump(testc, f)

# df = pd.read_csv('outcome.csv') # 生成的数据文件
#
# df_json = df['date']
# # df_json.astype()
# df_json.to_json(path_or_buf='outcometest.json',orient='values')
# # df_json2=json.loads(df_json)

# with open('outcometest.json','w') as file_obj:
#     json.dump(df_json2,file_obj)

# df2 = pd.read_json('outcome.json')
# df2.to_csv('outcome2.csv')
