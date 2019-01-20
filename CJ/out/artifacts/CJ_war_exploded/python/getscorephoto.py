#-*- coding: utf-8 -*-
import matplotlib.pyplot as plt
import pymysql
import sys
import numpy as np
import seaborn as sns
import requests

def getscorephoto(StudentNo):
    response = requests.get("http://www.baidu.com")
    print("url："+response.url)
    db = pymysql.connect(host="localhost",
                         user='root',
                         passwd="likecrr123,.",
                         port=3306,
                         db="richard",
                         charset='utf8')
    cursor = db.cursor()
    sql = "select substr(TestPaperNo,1,9)as batch,sum(score) from cj1_singlescore WHERE StudentNo=" + StudentNo + " group by SUBSTR(TestPaperNo,1,9) ORDER BY batch asc"
    cursor.execute(sql)
    result = cursor.fetchall()
    batchlist = []
    scorelist = []
    for i in result:
        batchlist.append(i[0])
        scorelist.append(i[1])

    plt.rcParams['font.sans-serif']=['SimHei']
    plt.rcParams['axes.unicode_minus'] = False
    sns.set_context(context="paper")
    sns.pointplot(batchlist, scorelist, color="pink", fontsize=14)
    plt.title("成绩变化折线图", fontsize=24, color="red")
    plt.xlabel('考试批次', fontsize=14, color="purple")
    plt.ylabel("总分", fontsize=14, color="blue")
    plt.tick_params(axis="both", labelsize=14)
    name = "C:\\Users\\10012\\Learning\\数据库课程实践\\CJ\\web\\score\\" + StudentNo + ".png"
    plt.savefig(name, bbox_inches="tight")

getscorephoto(sys.argv[1])
