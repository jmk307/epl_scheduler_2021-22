import csv

from selenium import webdriver
import time

Home = []
Away = []
StartDate = []
StartTime = []
HomeScore = []
AwayScore = []
result = []

url = 'https://sports.news.naver.com/wfootball/schedule/index?year=2021&month=08&category=epl'
driver = webdriver.PhantomJS('./phantomjs.exe')
driver.get(url)

startTime = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/td[1]/div/span[1]')

startDate = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/th/div/em')

home = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/td[2]/div/span[1]/span[2]')

away = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/td[2]/div/span[2]/span[2]')

homeScore = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/td[2]/div/span[1]/span[3]')

awayScore = driver.find_elements_by_xpath(
    '//*[@id="_monthlyScheduleList"]/tr/td[2]/div/span[2]/span[3]')

for i in startDate:
    print(i.text)

for i in home:
    Home.append(i.text)

for i in away:
    Away.append(i.text)

for i in homeScore:
    HomeScore.append(i.text)

for i in awayScore:
    AwayScore.append(i.text)

for i in range(len(HomeScore)):
    result.append(HomeScore[i] + ' - ' + AwayScore[i])

for i in startTime:
    if i.text == '경기가 없습니다.':
        continue
    StartTime.append(i.text)

f = open('epl.csv', 'w', newline='', encoding='utf-8')
wr = csv.writer(f)
for i in range(len(home)):
    wr.writerow([Home[i], Away[i], '0', StartTime[i], result[i]])
f.close()
