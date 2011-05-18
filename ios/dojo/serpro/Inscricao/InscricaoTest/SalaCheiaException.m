//
//  SalaCheiaException.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "SalaCheiaException.h"


@implementation SalaCheiaException

- (id) initWithLotacao:(int) aLotacao {
    NSString *message = [NSString stringWithFormat:@"A sala está lotada com %i alunos", aLotacao];
    self = [super initWithName:@"SalaCheiaException" reason:message userInfo:nil];
    [message release];
    
    if (self) {
        lotacao = aLotacao;
    }
    
    return self;
}

@end
