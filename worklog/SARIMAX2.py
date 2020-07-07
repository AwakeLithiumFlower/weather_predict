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

# Load data
df = pd.read_csv('maxmin2.csv')
df.ds = pd.to_datetime(df.date)
df.index = df.ds
df.drop(['date'], axis=1, inplace=True)
# Resampling
df_day = df.resample('W').mean()
# print(df_month)
# 拆分预测集及验证集
df_day_train = df_day['1995':'2000']
# print(df_month_test)
# print('df_month_test', len(df_month_test))
df_day_test = df_day['2000':'2001']
# print('df_month', len(df_month))
# PLOTS
# fig = plt.figure(figsize=[15, 7])
# plt.suptitle('sales, mean', fontsize=22)
#
# plt.plot(df_day.tmax, '-', label='By Months')
# plt.legend()

# plt.tight_layout()
# plt.show()
# # 看趋势
# plt.figure(figsize=[15, 7])
# sm.tsa.seasonal_decompose(df_month.y).plot()
# print("air_passengers test: p={}".format( adfuller(df_month.y)[1]))
# # air_passengers test: p=0.996129346920727
# # Box-Cox Transformations ts序列转换
# df_month['y_box'], lmbda = stats.boxcox(df_month.y)
# print("air_passengers test: p={}".format(adfuller(df_month.y_box)[1]))
# # air_passengers test: p=0.7011194980409873
# # Seasonal differentiation
# # 季节性差分确定sax中m参数
# df_month['y_box_diff'] = df_month['y_box'] - df_month['y_box'].shift(12)

# SARIMAX参数说明
'''
   趋势参数：（与ARIMA模型相同）
   p：趋势自回归阶数。
   d：趋势差分阶数。
   q：趋势移动平均阶数。

   季节性参数：
   P：季节性自回归阶数。
   D：季节性差分阶数。
   Q：季节性移动平均阶数。
   m：单个季节期间的时间步数。
'''
# # Initial approximation of parameters
# Qs = range(0, 3)
# qs = range(0, 3)
# Ps = range(0, 3)
# ps = range(0, 3)
# D = 1
# d = 1
#
# parameters = product(ps, qs, Ps, Qs)
# parameters_list = list(parameters)
# # list参数列表
# print('parameters_list:{}'.format(parameters_list))
# print(len(parameters_list))
#
# results = []
# best_aic = float("inf")

# for parameters in parameters_list:
#     try:
#         # SARIMAX 训练的时候用到转换之后的ts
#         model = SARIMAX(df_month.tmax, order=(parameters[0], d, parameters[1]),
#                                           seasonal_order=(parameters[2], D, parameters[3], 12)).fit(disp=-1)
#     except ValueError:
#         print('wrong parameters:', parameters)
#         continue
#
#     aic = model.aic
#     if aic < best_aic:
#         best_model = model
#         best_aic = aic
#         best_param = parameters
#     results.append([parameters, model.aic])
#
# result_table = pd.DataFrame(results)
# result_table.columns = ['parameters', 'aic']
# print(result_table.sort_values(by='aic', ascending=True).head())
# print(best_model.summary())

# bestModel:             SARIMAX(0, 1, 1)x(1, 1, 1, 12)

best_model=SARIMAX(df_day_train.tmax, order=(0, 1, 1),seasonal_order=(1, 1, 1, 52)).fit(disp=-1)

# tsa.plot_acf(best_model.resid[13:].values.squeeze(), lags=48,)
# # 下图是对残差进行的检验。可以确认服从正太分布，且不存在滞后效应。
# best_model.plot_diagnostics(lags=30, figsize=(16, 12))
# df_month2 = df_month_test[['tmax']]
# best_model.predict()  设定开始结束时间
# invboxcox函数用于还愿boxcox序列
# df_month2['forecast'] = invboxcox(best_model.forecast(steps=5), lmbda)
df_day2 = best_model.forecast(1000)
# plt.figure(figsize=(15, 7))
plt.plot(df_day2)
df_day_train.tmax.plot(color='r', ls='--', label='Origin')
plt.show()

# 获取mse

