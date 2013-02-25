from django.http import HttpResponse

def interpret(request):
    response = HttpResponse('This is a placeholder. You submitted: ')
    response.write(request.GET.get('phrase', ''))
    return response
