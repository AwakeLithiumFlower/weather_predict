#
import pandas as pd
from datetime import datetime
from dateutil import parser
# 数据导入
data_raw=pd.read_csv('2198120.csv',encoding='utf-8')
# 数据预处理
data_raw['date']=data_raw['DATE'].apply(parser.parse)
data_raw['tmax']=data_raw['TMAX'].astype(float)
data_raw['tmin']=data_raw['TMIN'].astype(float)
data=data_raw.loc[:,['date','tmax','tmin']]
data=data[(pd.Series.notnull(data['tmax']))&(pd.Series.notnull(data['tmin']))]
# data.query("date.dt.day==28&date.dt.month==6",inplace=True)
# 数据导出
data.to_csv('maxmin3.csv',index=None)