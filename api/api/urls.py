from django.conf.urls import patterns, include, url

urlpatterns = patterns(
    '',

    url(r'^parse/', include('parse.urls')),
    url(r'interpret/', include('interpret.urls')),
)
