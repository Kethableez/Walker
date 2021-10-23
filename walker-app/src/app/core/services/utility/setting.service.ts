import { ActionResponse } from './../../../models/action-response.model';
import { environment } from '../../../../environments/environment';
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SettingService {
  private static finalEnv = environment;

  constructor() {};

  public getAuthUrl(action: string, params?: any) {
    return this.getUrl('auth', action, params);
  }

  public getUserUrl(action: string, params?: any) {
    return this.getUrl('user', action, params);
  }

  public getOwnerUrl(action: string, params?: any) {
    return this.getUrl('owner', action, params);
  }

  public getSitterUrl(action: string, params?: any) {
    return this.getUrl('sitter', action, params);
  }

  public getDogUrl(action: string, params?: any) {
    return this.getUrl('dog', action, params);
  }

  public getWalkUrl(action: string, params?: any) {
    return this.getUrl('walk', action, params);
  }

  public getReviewUrl(action: string, params?: any) {
    return this.getUrl('review', action, params);
  }

  public getImageUrl(action: string, params?: any) {
    return this.getUrl('image', action, params);
  }

  public getAdminUrl(action: string, params?: any) {
    return this.getUrl('admin', action, params);
  }

  private getUrl(prefix: string, action: string, params?: any) {
    const preparedCall = this.injectParams(this.getCall(prefix, action), params);
    const finalUrl = [this.baseUrl, preparedCall].join('/');
    return finalUrl;
  }

  private injectParams(call: string, params?: any): string {
    const callWithParams: string[] = [];

    call.split('/').forEach(el => callWithParams.push(el.startsWith(':') ? params[el.replace(':', '')] : el));

    return callWithParams.join('/');
  }

  private getCall(prefix: string, action: string): string {
    const call = this.getPrefixAndCall(prefix, action);
    return [call.prefix, call.call].join('/');
  }

  private getPrefixAndCall(prefix: string, call: string) {
    return { prefix: this.url[prefix].prefix, call: this.url[prefix].calls[call] };
  }

  private get url() {
    return SettingService.finalEnv.urls;
  }

  private get baseUrl() {
    return SettingService.finalEnv.apiBaseUrl;
  }
}
