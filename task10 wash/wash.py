import pandas as  pd
from datetime import datetime
from dateutil import parser

data_raw = pd.read_csv(r'D:\2020夏小学期\2198087.csv',encoding='utf-8')
data = data_raw.loc[:,['DATE','TMAX','TMIN']]
data = data[(pd.Series.notnull(data['TMAX']))&(pd.Series.notnull(data['TMIN']))]
data.to_csv(r'D:\2020夏小学期\wash.csv',index=None)
print("done")