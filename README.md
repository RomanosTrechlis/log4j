# log4j

It adds an RPCAppender that extends the WriteAppender. 
This requires an additional host and port parameters in order to work for a remote LogScribe.
Instead of writing on a file, we override the subAppend method to call the LogScribe Client and delegate the writting of logs to logScribe.

### TODO

* change constactors to take additional arguments host and port.
* LogScribeClient is rather thin and several things can be implemented.

  1. add a blocking stub in addition to the asynchronous stub.
  2. make the StreamObserver do something onError, onNext, and  onCompete.
