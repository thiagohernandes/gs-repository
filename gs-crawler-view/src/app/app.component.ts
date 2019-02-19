import { Component, OnDestroy } from '@angular/core';
import { UtilService } from './services/util';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnDestroy {

  title = 'gs-crawler-view';
  public loadingService = false;
  private _subscriptionLoading: Subscription;

  constructor(private utilService: UtilService) {
    this._subscriptionLoading = this.utilService.getLoading().subscribe((load: boolean) => {
        this.loadingService = load;
    });
  }

  ngOnDestroy() {
    this._subscriptionLoading.unsubscribe();
  }

}
