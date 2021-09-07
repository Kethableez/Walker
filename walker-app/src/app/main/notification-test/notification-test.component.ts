import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Component, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

const subUrl = 'http://localhost:8080/notification/subcribe'

@Component({
  selector: 'ktbz-notification-test',
  templateUrl: './notification-test.component.html',
  styleUrls: ['./notification-test.component.scss']
})
export class NotificationTestComponent implements OnInit {

  constructor(private http: HttpClient, private _zone: NgZone, private route: ActivatedRoute, private c: ChangeDetectorRef) { }

  id = this.route.snapshot.params.id;
  list: string[] = []


  ngOnInit(): void {
    let sse = new EventSource(subUrl + '?userId=' + this.id);
    sse.onmessage = event => {
      this._zone.run(() => {
        console.log(event);
      });
    };
    // this.getServerSentEvent(subUrl, this.id).subscribe({
    //   next: any => console.log(any),
    //   error: err => console.log(err)
    // });

  //   let observable = Observable.create((observer: { next: (arg0: void) => any; error: (arg0: void) => any; }) => {
  //     const eventSource = new EventSource(subUrl + '?userId=' + this.id);
  //     eventSource.onmessage = x => observer.next(console.log(x));
  //     eventSource.onerror = x => observer.error(console.log('EventSource failed'));

  //     return () => {
  //         eventSource.close();
  //     };
  // });
  // observable.subscribe({
  //     next: (aString: any) => console.log(aString),
  //     error: (err: string) => console.error('something wrong occurred: ' + err)
  // });
  }

  getServerSentEvent(url: string, userId: string): Observable<any> {

    return Observable.create((observer: { next: (arg0: MessageEvent<any>) => void; error: (arg0: Event) => void; }) => {
      const eventSource = this.getEventSource(url, userId);
      eventSource.onmessage = event => {
        this._zone.run(() => {
          observer.next(event);
        });
      };
      eventSource.onerror = error => {
        this._zone.run(() => {
          observer.error(error);
        });
      };
    });

  }
  private getEventSource(url: string, userId: string): EventSource {
    // return new EventSource(url);
    return new EventSource(url + '?userId=' + userId);
  }

}
