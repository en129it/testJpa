
interface Clipboard {
    writeText(text: string): Promise<void>;
}

interface NavigatorClipboard extends Navigator {
    readonly clipboard?: Clipboard;
}

export enum OS {
    Windows = 1,
    MacOS = 2,
    Other = 3
}

export function getOS(): OS {
    if ('platform' in window.navigator) {
        let platform = window.navigator.platform;
        if (platform != null) {
            platform = platform.toUpperCase();
            if (platform.startsWith('WIN')) {
                return OS.Windows;
            } else if (platform.startsWith('MAC')) {
                return OS.MacOS;
            }
        }
    }
    const userAgent = window.navigator.userAgent.toUpperCase();
    if (userAgent.indexOf('WIN') !== -1) {
        return OS.Windows;
    } else if (userAgent.indexOf('MAC') !== -1) {
        return OS.MacOS;
    }
    return OS.Other;
}

export function copyToClipboard(textElem: HTMLInputElement): Promise<void> {

    if ('clipboard' in window.navigator) {
        return (window.navigator as NavigatorClipboard).clipboard.writeText(textElem.value);
    } else {
        textElem.select();
        window.document.execCommand('copy');
        const selection = window.getSelection();
        if (selection != null) {
            selection.removeAllRanges();
        }
        return Promise.resolve();
    }
}

/*
<div>
    <input type="text" id="copy" name="copy" placeholder="Enter text to copy to clipbard">
    <button type="button" (click)="copyToClipboard()">Copy to clipboard</button>
</div>
<div>
    <button type="button" (click)="printOS()">Get OS</button>
    <input type="text" id="os" name="os" placeholder="Enter text to copy to clipbard" [(ngModel)]="os">
</div>


  public copyToClipboard() {
    const elem = this.document.getElementById('copy') as HTMLInputElement;
    copyToClipboard(elem).finally( () => {
      console.log('Copy to clipboard done');
    });
  }


  public printOS() {
    const osRslt = getOS();
    if (OS.Windows === osRslt) {
      this.os = 'Windows OS';
    } else if (OS.MacOS === osRslt) {
      this.os = 'Mac OS';
    } else  {
      this.os = 'Other';
    }
  }
*/
