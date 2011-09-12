//
//  CasaInvalidaException.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "CasaInexistenteException.h"

@implementation CasaInexistenteException

- (id)init
{
    self = [super initWithName:@"Casa inválida!" reason:nil userInfo:nil];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

@end
