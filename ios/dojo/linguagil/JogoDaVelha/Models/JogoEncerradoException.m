//
//  JogoEncerradoException.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "JogoEncerradoException.h"

@implementation JogoEncerradoException

- (id)init
{
    self = [super initWithName:@"O jogo já foi finalizado" reason:nil userInfo:nil];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

@end
