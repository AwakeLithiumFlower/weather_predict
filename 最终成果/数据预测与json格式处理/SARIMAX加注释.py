import pandas as pd
import warnings
warnings.filterwarnings('ignore')
import matplotlib.pylab as plt
from statsmodels.tsa.statespace.sarimax import SARIMAX

# Load data
#打开文件
df = pd.read_csv('长春.csv',encoding='utf-8')
#加载date到时间轴
df.ds = pd.to_datetime(df.date)
df.index = df.ds
#加载tmax到变量
df['平均气温'].astype('double')#1
#展示训练前数据
df.drop(['date'], axis=1, inplace=True)
df.平均气温.plot(color='r', ls='--', label='Origin')#1
plt.show()
#按周拆分
df_day = df.resample('D').mean()
# 拆分出训练数据
df_day_train = df_day['2017-5-31':'2020-5-31']
#经检测的最优训练模型
best_model=SARIMAX(df_day_train.平均气温, order=(1, 1, 1),seasonal_order=(1, 1, 1, 90)).fit(disp=-1)
# 预测未来500个单位的数据
df_day2 = best_model.forecast(90)
# plt.figure(figsize=(15, 7))
#数据展示
plt.plot(df_day2)
df_day_train.平均气温.plot(color='r', ls='--', label='Origin')#1
#保存图片
plt.savefig('长春daytave.png')#2
plt.show()
# 将预测数据切片
df_day2=df_day2['2020-5-31':'2025-5-31']
#保存预测数据
df_day2.to_csv('长春daytave.csv')#2
