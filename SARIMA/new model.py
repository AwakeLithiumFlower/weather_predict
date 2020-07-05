
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import datetime
from dateutil.relativedelta import relativedelta
import seaborn as sns
import statsmodels.api as sm
from statsmodels.tsa.stattools import acf
from statsmodels.tsa.stattools import pacf
from statsmodels.tsa.seasonal import seasonal_decompose
data = pd.read_csv(r'C:\Users\36334\Desktop\wash.csv', index_col='DATE', parse_dates=['DATE'])
print(data.head())
data['TMAX'].plot(figsize=(12,8), title= 'TMAX-all', fontsize=14)
plt.savefig('TMAX-all', bbox_inches='tight')
decomposition = seasonal_decompose(data['TMAX'], freq=12)
fig = plt.figure()
fig = decomposition.plot()
fig.set_size_inches(12, 6)

from statsmodels.tsa.stattools import adfuller
def test_stationarity(timeseries):

 rolmean = timeseries.rolling(window=12).mean()
 rolstd = timeseries.rolling(window=12).std()
#Plot rolling statistics:
 fig = plt.figure(figsize=(12, 8))
 orig = plt.plot(timeseries, color='blue',label='Original')
 mean = plt.plot(rolmean, color='red', label='Rolling Mean')
 std = plt.plot(rolstd, color='black', label = 'Rolling Std')
 plt.legend(loc='best')
 plt.title('Rolling Mean & Standard Deviation')
 plt.show()
#Perform Dickey-Fuller test:
 print('Results of Dickey-Fuller Test:')
 dftest = adfuller(timeseries, autolag='AIC')
 dfoutput = pd.Series(dftest[0:4], index=['Test Statistic','p-value','#Lags Used','Number of Observations Used'])
 for key,value in dftest[4].items():
  dfoutput['Critical Value (%s)'%key] = value
 print(dfoutput)
test_stationarity(data['TMAX'])
#一阶差分
data['first_difference'] = data['TMAX'].diff(1)
test_stationarity(data['first_difference'].dropna(inplace=False))
'''#季节差分
data['seasonal_difference'] = data
['riders'].diff(12)
test_stationarity(data['seasonal_difference'].dropna(inplace=False))'''
#一阶加季节
data['seasonal_first_difference'] = data['first_difference'].diff(12)
test_stationarity(data['seasonal_first_difference'].dropna(inplace=False))
#取对数
data['TMAX_log']= np.log(data['TMAX'])
test_stationarity(data['TMAX_log'])
#对数季节差分
data['log_seasonal_difference'] = data['TMAX_log'].diff(12)
test_stationarity(data['log_seasonal_difference'].dropna(inplace=False))
#找参数
fig = plt.figure(figsize=(12,8))
ax1 = fig.add_subplot(211)
fig = sm.graphics.tsa.plot_acf(data['seasonal_first_difference'].iloc[13:], lags=40, ax=ax1)
#从13开始是因为做季节性差分时window是12
ax2 = fig.add_subplot(212)
fig = sm.graphics.tsa.plot_pacf(data['seasonal_first_difference'].iloc[13:], lags=40, ax=ax2)
#创建模型并预测
mod = sm.tsa.statespace.SARIMAX(data['TMAX'], trend='n', order=(0,1,1), seasonal_order=(1,1,1,52))
results = mod.fit(disp=-1)
print(results.summary())
data['forecast'] = results.predict(start = 102, end= 114, dynamic= True)
data[['TMAX', 'forecast']].plot(figsize=(12, 6))
#新值预测
start = datetime.datetime.strptime("2020-05-01", "%Y-%m-%d")
date_list = [start + relativedelta(months=x) for x in range(0,12)]
future = pd.DataFrame(index=date_list, columns= data.columns)
data = pd.concat([data, future])
data['forecast'] = results.predict(start = 114, end = 2500, dynamic= True)
data[['TMAX', 'forecast']].plot(figsize=(12, 8))
plt.show()