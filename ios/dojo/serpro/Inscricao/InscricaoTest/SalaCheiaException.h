//
//  SalaCheiaException.h
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface SalaCheiaException : NSException {

    int lotacao;

}

- (id) initWithLotacao:(int) lotacao;

@end
