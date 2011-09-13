//
//  Casa32View.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Casa32View.h"
#import "TabuleiroView.h"

@implementation Casa32View

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    TabuleiroView *view = (TabuleiroView *)[self superview];

    if([view jogadaPermitidaNaLinha:3 eColuna:2]){
        self.image = [UIImage imageNamed:[view proximaMarcacao]];
        
        [view marcarLinha:3 eColuna:2];
    }
}

@end
