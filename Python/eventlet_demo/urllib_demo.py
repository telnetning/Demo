urls = [
    "http://baidu.com/",
    "http://douban.com/"
]

import eventlet
from eventlet.green import urllib2

def fetch(url):
    return urllib2.urlopen(url).read()

pool = eventlet.GreenPool()
for body in pool.imap(fetch, urls):
    print("got body", len(body))