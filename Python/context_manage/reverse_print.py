import sys

'''
Context manage aimed to repleade 
    try/except/finally
statement.
'''

class reverse_print:
    def __enter__(self):
        self.origin_writer = sys.stdout.write
        sys.stdout.write = self.reverse_writer  # change write behavior
        return "World"  # return to WITH statement

    def reverse_writer(self, text):
        self.origin_writer(text[::-1])  # output text reverse
        
    def __exit__(self, exc_type, exc_val, exc_tb):
        '''
        :param exc_type: exception type
        :param exc_val: exception instance
        :param exc_tb: exception traceback
        :return: 
        '''
        sys.stdout.write = self.origin_writer


with reverse_print() as what:
    print(what)
    print("hello")
    
print("Hello, Context")

'''OUTPUT
dlroW
olleh
Hello, Context
'''
