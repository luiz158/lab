//
//  Casa12View.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Casa12View.h"
#import "TabuleiroView.h"

@implementation Casa12View

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    TabuleiroView *view = (TabuleiroView *)[self superview];
    
    if([view jogadaPermitidaNaLinha:1 eColuna:2]){
        NSString *image = [view proximaMarcacao];
        self.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:image]];
        
        [view marcarLinha:1 eColuna:2];
    }
}

@end
