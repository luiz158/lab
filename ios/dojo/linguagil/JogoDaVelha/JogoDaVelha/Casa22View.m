//
//  Casa22View.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Casa22View.h"
#import "TabuleiroView.h"

@implementation Casa22View

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    TabuleiroView *view = (TabuleiroView *)[self superview];

    if([view jogadaPermitidaNaLinha:2 eColuna:2]){
        self.image = [UIImage imageNamed:[view proximaMarcacao]];
        
        [view marcarLinha:2 eColuna:2];
    }
}

@end
