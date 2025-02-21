# Remove server header from response and any other headers that may leak information

class RemoveHeadersMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        response = self.get_response(request)
        del response["Server"]
        response['X-Frame-Options'] = 'SAMEORIGIN'
        response['X-Content-Type-Options'] = 'nosniff'
        response['X-XSS-Protection'] = '1; mode=block'
        response['Referrer-Policy'] = 'no-referrer'
        response['Content-Security-Policy'] = "default-src 'self'"
        return response


