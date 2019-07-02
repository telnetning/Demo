class custom_handler:
    def __init__(self, worker_name):
        self.worker_name = worker_name

    def __enter__(self):
        return self  # __enter__ result returned to WITH stmt

    def __exit__(self, exc_type, exc_val, exc_tb):
        print("Exit")  # always run if __enter__ ran ok


with custom_handler("worker001") as ch:  # ch is result of __enter__()
    print("Enter {}".format(ch.worker_name))
    print("Do some task")
    raise Exception("Exception by myself.")

'''
Enter worker001
Do some task
Exception: Exception by myself.
Exit
'''
