from urllib import urlencode
from urllib2 import urlopen

from django.http import HttpResponse

from django.conf import settings

def parse(request):
    phrase = request.GET.get('phrase')
    query_url = '{0}?{1}'.format(settings.PARSER_BACKEND_URL,
                                 urlencode({'phrase': phrase}))

    xml = urlopen(query_url).read()  # let any URLErrors propagate

    return HttpResponse(xml, content_type='text/xml')
